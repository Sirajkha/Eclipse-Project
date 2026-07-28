package com.irctc_booking.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irctc_booking.request.BookingRequest;
import com.irctc_booking.response.BookingResponse;
import com.irctc_booking.service.BookingService;

@RestController
@RequestMapping("/irctc/booking/")
public class BookingController {

	final BookingService bookingService;

	BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	@PostMapping("bookTicket")
	public BookingResponse doTicketBooking(@RequestBody BookingRequest request) {
		return bookingService.doBooking(request);
	}
}
