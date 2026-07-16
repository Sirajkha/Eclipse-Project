package com.irctc_booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.irctc_booking.entity.BookingEntity;
import com.irctc_booking.repository.BookingRepository;
import com.irctc_booking.request.BookingRequest;
import com.irctc_booking.response.BookingResponse;

@Service
public class BookingService {

	final BookingRepository bookingRepository;

	BookingService(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}

	public BookingResponse doBooking(BookingRequest request) {

		BookingEntity bookingEntity = new BookingEntity();
		bookingEntity.setFromStation(request.getForm());
		bookingEntity.setToStation(request.getTo());
		bookingEntity.setPassengerName(request.getPassengerName());
		bookingEntity.setAge(request.getAge());
		bookingEntity.setGender(request.getGender());
		bookingEntity.setJourneyDate(request.getDate());
		bookingEntity.setTravelClass(request.getTravelClass());
		bookingEntity.setPnr(generatePnr());
		bookingEntity.setUserId(request.getUserId());

		bookingRepository.save(bookingEntity);

		BookingResponse response = new BookingResponse();
		response.setBookingId(bookingEntity.getBookingId());
		response.setPnrNumber(bookingEntity.getPnr());
		response.setBookingStatus("CONFIRMED"); // Or WAITING, RAC, etc.
		response.setJourneyDate(bookingEntity.getJourneyDate());
		response.setCoach("B2");
		response.setSeatNumber("32");
		response.setMessage("Ticket booked successfully.");

		return response;

	}

	public static String generatePnr() {
		Random random = new Random();
		long pnr = 1000000000L + (long) (random.nextDouble() * 9000000000L);
		return String.valueOf(pnr);
	}

	public List<BookingResponse> getTickets(String userId, String pageNumber, String pageSize) {

		Pageable pageable = PageRequest.of(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));

//		Page<BookingEntity> tickets = bookingRepository.findAll(pageable);

		Page<BookingEntity> tickets = bookingRepository.findByUserId(userId, pageable);
		
//		List<BookingEntity> entities = bookingRepository.findByUserId(userId);
		
		List<BookingResponse> responses = new ArrayList<BookingResponse>();

//		for (BookingEntity entity : entities) {
//			BookingResponse response = new BookingResponse();
//			response.setBookingId(entity.getBookingId());
//			response.setPnrNumber(entity.getPnr());
//			response.setBookingStatus("CONFIRMED"); // Or WAITING, RAC, etc.
//			response.setJourneyDate(entity.getJourneyDate());
//			response.setCoach("B2");
//			response.setSeatNumber("32");
//			response.setMessage("Ticket booked successfully.");
//
//			responses.add(response);
//		}

		for (BookingEntity entity : tickets) {
			BookingResponse response = new BookingResponse();
			response.setBookingId(entity.getBookingId());
			response.setPnrNumber(entity.getPnr());
			response.setBookingStatus("CONFIRMED"); // Or WAITING, RAC, etc.
			response.setJourneyDate(entity.getJourneyDate());
			response.setCoach("B2");
			response.setSeatNumber("32");
			response.setMessage("Ticket booked successfully.");

			responses.add(response);
		}
		return responses;
		
	} 

}
