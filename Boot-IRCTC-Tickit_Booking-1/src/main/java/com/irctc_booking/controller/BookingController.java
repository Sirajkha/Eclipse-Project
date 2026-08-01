package com.irctc_booking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irctc_booking.request.BookingRequest;
import com.irctc_booking.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

	final BookingService bookingService;

	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
		System.out.println("System is loading......");
	}

	@PostMapping("/createBooking")
	public ResponseEntity<String> createBooking(@RequestBody BookingRequest request) {
		String booking = bookingService.createBooking(request);
		System.out.println("==== Controller Hit ====");
		return ResponseEntity.ok(booking);
	}

	@PostMapping("/cancel/{pnr}")
	public ResponseEntity<String> cancelBooking(@PathVariable String pnr) {
		String cancel = bookingService.cancelBooking(pnr);
		return ResponseEntity.ok(cancel);
	}
}
