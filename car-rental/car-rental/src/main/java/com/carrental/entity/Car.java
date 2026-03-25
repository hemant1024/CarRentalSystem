package com.carrental.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleModel;

    @Column(unique = true)
    private String vehicleNumber;

    private int seatingCapacity;

    private double rentPerDay;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    private User agency;
}