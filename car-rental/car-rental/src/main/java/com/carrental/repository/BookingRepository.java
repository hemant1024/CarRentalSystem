package com.carrental.repository;

import com.carrental.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCar_Agency_Id(Long agencyId);
    List<Booking> findByCar_Id(Long carId);
    List<Booking> findByCustomer_Id(Long customerId);
}