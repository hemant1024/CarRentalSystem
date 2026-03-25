package com.carrental.controller;

import com.carrental.dto.BookingRequest;
import com.carrental.dto.BookingResponse;
import com.carrental.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(
            BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public String bookCar(
            @Valid @RequestBody BookingRequest request,
            Authentication authentication) {

        Long customerId =
                (Long) authentication.getPrincipal();

        return bookingService.bookCar(
                request,
                customerId
        );
    }

    @DeleteMapping("/{bookingId}")
    public String cancelBooking(
            @PathVariable Long bookingId,
            Authentication authentication) {

        Long customerId =
                (Long) authentication.getPrincipal();

        // For now we trust the customer.
        // Later we can verify ownership.

        return bookingService
                .cancelBooking(bookingId);
    }

    @GetMapping
    public List<BookingResponse> getCustomerBookings(
            Authentication authentication) {

        Long customerId =
                (Long) authentication.getPrincipal();

        return bookingService
                .getBookingsForCustomer(
                        customerId);
    }
}