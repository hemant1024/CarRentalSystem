package com.carrental.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookingResponse {

    private Long bookingId;

    private String customerName;

    private String vehicleModel;

    private LocalDate startDate;

    private int numberOfDays;

    private double totalCost;
}