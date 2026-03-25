package com.carrental.controller;

import com.carrental.dto.AddCarRequest;
import com.carrental.dto.CarResponse;
import com.carrental.dto.UpdateCarRequest;
import com.carrental.entity.Car;
import com.carrental.service.CarService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public String addCar(
            @Valid @RequestBody AddCarRequest request,
            Authentication authentication) {

        Long agencyId =
                (Long) authentication.getPrincipal();

        return carService.addCar(
                request,
                agencyId
        );
    }

    @GetMapping
    public List<CarResponse> getAllCars() {

        return carService.getAllCars();
    }

    @GetMapping("/available")
    public List<CarResponse> getAvailableCars(
            @RequestParam LocalDate startDate,
            @RequestParam int numberOfDays) {

        return carService.getAvailableCars(
                startDate,
                numberOfDays
        );
    }

    @DeleteMapping("/{carId}")
    public String deleteCar(
            @PathVariable Long carId,
            Authentication authentication) {

        Long agencyId =
                (Long) authentication.getPrincipal();

        return carService.deleteCar(
                carId,
                agencyId);
    }

    @PutMapping("/{carId}")
    public String updateCar(
            @PathVariable Long carId,
            @RequestBody UpdateCarRequest request,
            Authentication authentication) {

        Long agencyId =
                (Long) authentication.getPrincipal();

        return carService.updateCar(
                carId,
                request,
                agencyId);
    }
}