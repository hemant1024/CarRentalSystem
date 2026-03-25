package com.carrental.controller;

import com.carrental.dto.BookingResponse;
import com.carrental.service.BookingService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/agency")
public class AgencyController {

    private final BookingService bookingService;

    public AgencyController(
            BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/bookings")
    public List<BookingResponse> getBookings(
            Authentication authentication) {

        Long agencyId =
                (Long) authentication.getPrincipal();

        return bookingService
                .getBookingsForAgency(
                        agencyId
                );
    }
}