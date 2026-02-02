package com.SPATanalytics.backend.controller;

import com.SPATanalytics.backend.model.Booking;
import com.SPATanalytics.backend.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.saveBooking(booking);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/date/{date}")
    public List<Booking> getBookingsByDate(@PathVariable String date) {
        return bookingService.getBookingsByDate(LocalDate.parse(date));
    }
}
