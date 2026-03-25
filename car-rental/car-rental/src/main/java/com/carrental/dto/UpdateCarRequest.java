package com.carrental.dto;

import lombok.Data;

@Data
public class UpdateCarRequest {

    private String vehicleModel;

    private String vehicleNumber;

    private int seatingCapacity;

    private double rentPerDay;
}