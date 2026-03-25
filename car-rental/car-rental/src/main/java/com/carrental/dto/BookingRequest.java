package com.carrental.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRequest {

    @Positive(message = "Car ID must be positive")
    private Long carId;

    @FutureOrPresent(
            message = "Start date cannot be in the past"
    )
    private LocalDate startDate;

    @Positive(
            message = "Number of days must be greater than 0"
    )
    private int numberOfDays;
}