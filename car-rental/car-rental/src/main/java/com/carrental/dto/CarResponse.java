package com.carrental.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarResponse {

    private Long id;

    private String vehicleModel;

    private String vehicleNumber;

    private int seatingCapacity;

    private double rentPerDay;

    private String agencyName;
}