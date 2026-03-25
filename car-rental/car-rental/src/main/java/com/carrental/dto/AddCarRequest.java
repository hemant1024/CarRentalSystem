package com.carrental.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddCarRequest {

    @NotBlank
    private String vehicleModel;

    @NotBlank
    private String vehicleNumber;

    @Positive
    private int seatingCapacity;

    @Positive
    private double rentPerDay;
}