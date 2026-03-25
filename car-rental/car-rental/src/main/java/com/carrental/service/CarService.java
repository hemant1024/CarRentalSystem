package com.carrental.service;

import com.carrental.dto.AddCarRequest;
import com.carrental.dto.CarResponse;
import com.carrental.dto.UpdateCarRequest;
import com.carrental.entity.Booking;
import com.carrental.entity.Car;
import com.carrental.entity.User;
import com.carrental.repository.BookingRepository;
import com.carrental.repository.CarRepository;
import com.carrental.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    public CarService(
            CarRepository carRepository,
            UserRepository userRepository,
            BookingRepository bookingRepository) {

        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

    public String addCar(AddCarRequest request,
                         Long agencyId) {

        User agency =
                userRepository.findById(agencyId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Agency not found"));

        Car car = Car.builder()
                .vehicleModel(request.getVehicleModel())
                .vehicleNumber(request.getVehicleNumber())
                .seatingCapacity(request.getSeatingCapacity())
                .rentPerDay(request.getRentPerDay())
                .agency(agency)
                .build();

        carRepository.save(car);

        return "Car added successfully";
    }

    public List<CarResponse> getAllCars() {

        List<Car> cars =
                carRepository.findAll();

        return cars.stream()
                .map(car ->
                        CarResponse.builder()
                                .id(car.getId())
                                .vehicleModel(
                                        car.getVehicleModel())
                                .vehicleNumber(
                                        car.getVehicleNumber())
                                .seatingCapacity(
                                        car.getSeatingCapacity())
                                .rentPerDay(
                                        car.getRentPerDay())
                                .agencyName(
                                        car.getAgency()
                                                .getName())
                                .build()
                )
                .toList();
    }

    public List<CarResponse> getAvailableCars(
            LocalDate startDate,
            int numberOfDays) {

        LocalDate newStart = startDate;

        LocalDate newEnd =
                newStart.plusDays(
                        numberOfDays - 1
                );

        List<Car> allCars =
                carRepository.findAll();

        List<Booking> bookings =
                bookingRepository.findAll();

        return allCars.stream()
                .filter(car -> {

                    List<Booking> carBookings =
                            bookings.stream()
                                    .filter(b ->
                                            b.getCar()
                                                    .getId()
                                                    .equals(car.getId()))
                                    .toList();

                    for (Booking booking : carBookings) {

                        LocalDate existingStart =
                                booking.getStartDate();

                        LocalDate existingEnd =
                                existingStart.plusDays(
                                        booking.getNumberOfDays() - 1
                                );

                        boolean overlap =
                                !newEnd.isBefore(existingStart)
                                        && !newStart.isAfter(existingEnd);

                        if (overlap) {
                            return false;
                        }
                    }

                    return true;
                })
                .map(car ->
                        CarResponse.builder()
                                .id(car.getId())
                                .vehicleModel(
                                        car.getVehicleModel())
                                .vehicleNumber(
                                        car.getVehicleNumber())
                                .seatingCapacity(
                                        car.getSeatingCapacity())
                                .rentPerDay(
                                        car.getRentPerDay())
                                .agencyName(
                                        car.getAgency()
                                                .getName())
                                .build()
                )
                .toList();
    }

    public String deleteCar(
            Long carId,
            Long agencyId) {

        Car car =
                carRepository
                        .findById(carId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Car not found"));

        if (!car.getAgency()
                .getId()
                .equals(agencyId)) {

            throw new RuntimeException(
                    "You are not authorized to delete this car");
        }

        carRepository.delete(car);

        return "Car deleted successfully";
    }

    public String updateCar(
            Long carId,
            UpdateCarRequest request,
            Long agencyId) {

        Car car =
                carRepository
                        .findById(carId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Car not found"));

        if (!car.getAgency()
                .getId()
                .equals(agencyId)) {

            throw new RuntimeException(
                    "You are not authorized to update this car");
        }

        car.setVehicleModel(
                request.getVehicleModel());

        car.setVehicleNumber(
                request.getVehicleNumber());

        car.setSeatingCapacity(
                request.getSeatingCapacity());

        car.setRentPerDay(
                request.getRentPerDay());

        carRepository.save(car);

        return "Car updated successfully";
    }
}