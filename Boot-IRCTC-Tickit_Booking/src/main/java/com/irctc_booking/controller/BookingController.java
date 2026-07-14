package com.irctc_booking.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@PostMapping("confirmTicket")
	public BookingResponse doBooking(@RequestBody BookingRequest request) {
   System.out.println("testing the jenkins.....");
		return bookingService.doBooking(request);
	}

	@GetMapping("getTickets")
	public List<BookingResponse> getAllBooking(@RequestParam String userId, @RequestParam String pageNumber,
			@RequestParam String pageSize) {
		return bookingService.getTickets(userId, pageNumber, pageSize);
	}
}
