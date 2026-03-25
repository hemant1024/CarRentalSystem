package com.carrental.service;

import com.carrental.dto.BookingRequest;
import com.carrental.dto.BookingResponse;
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
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public BookingService(
            BookingRepository bookingRepository,
            CarRepository carRepository,
            UserRepository userRepository) {

        this.bookingRepository = bookingRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    public String bookCar(
            BookingRequest request,
            Long customerId) {

        Car car = carRepository
                .findById(request.getCarId())
                .orElseThrow(() ->
                        new RuntimeException("Car not found"));

        User customer = userRepository
                .findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        double totalCost =
                car.getRentPerDay()
                        * request.getNumberOfDays();

        Booking booking = Booking.builder()
                .car(car)
                .customer(customer)
                .startDate(request.getStartDate())
                .numberOfDays(request.getNumberOfDays())
                .totalCost(totalCost)
                .build();

        if (request.getNumberOfDays() <= 0) {
            throw new RuntimeException(
                    "Number of days must be greater than 0"
            );
        }

        LocalDate newStart =
                request.getStartDate();

        LocalDate newEnd =
                newStart.plusDays(
                        request.getNumberOfDays() - 1
                );

        List<Booking> existingBookings =
                bookingRepository
                        .findByCar_Id(
                                request.getCarId()
                        );

        for (Booking booking1 : existingBookings) {

            LocalDate existingStart =
                    booking1.getStartDate();

            LocalDate existingEnd =
                    existingStart.plusDays(
                            booking1.getNumberOfDays() - 1
                    );

            boolean overlap =
                    !newEnd.isBefore(existingStart)
                            && !newStart.isAfter(existingEnd);

            if (overlap) {
                throw new RuntimeException(
                        "Car is not available for selected dates"
                );
            }
        }

        bookingRepository.save(booking);

        return "Car booked successfully";
    }

    public List<BookingResponse> getBookingsForAgency(
            Long agencyId) {

        List<Booking> bookings =
                bookingRepository
                        .findByCar_Agency_Id(
                                agencyId
                        );

        return bookings.stream()
                .map(booking ->
                        BookingResponse.builder()
                                .bookingId(
                                        booking.getId())
                                .customerName(
                                        booking
                                                .getCustomer()
                                                .getName())
                                .vehicleModel(
                                        booking
                                                .getCar()
                                                .getVehicleModel())
                                .startDate(
                                        booking
                                                .getStartDate())
                                .numberOfDays(
                                        booking
                                                .getNumberOfDays())
                                .totalCost(
                                        booking
                                                .getTotalCost())
                                .build()
                )
                .toList();
    }

    public String cancelBooking(Long bookingId) {

        Booking booking =
                bookingRepository
                        .findById(bookingId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Booking not found"));

        bookingRepository.delete(booking);

        return "Booking cancelled successfully";
    }

    public List<BookingResponse> getBookingsForCustomer(
            Long customerId) {

        List<Booking> bookings =
                bookingRepository
                        .findByCustomer_Id(
                                customerId);

        return bookings.stream()
                .map(booking ->
                        BookingResponse.builder()
                                .bookingId(
                                        booking.getId())
                                .customerName(
                                        booking
                                                .getCustomer()
                                                .getName())
                                .vehicleModel(
                                        booking
                                                .getCar()
                                                .getVehicleModel())
                                .startDate(
                                        booking
                                                .getStartDate())
                                .numberOfDays(
                                        booking
                                                .getNumberOfDays())
                                .totalCost(
                                        booking
                                                .getTotalCost())
                                .build()
                )
                .toList();
    }
}