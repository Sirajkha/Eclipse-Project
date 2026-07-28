package com.irctc_booking.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.irctc_booking.entity.BookingEntity;
import com.irctc_booking.entity.PaymentEntity;
import com.irctc_booking.exception.InsufficientBanlanceException;
import com.irctc_booking.kafka.producer.service.KafkaService;
import com.irctc_booking.repository.BookingRepository;
import com.irctc_booking.repository.PaymentRepository;
import com.irctc_booking.request.BookingRequest;
import com.irctc_booking.response.BookingResponse;

import jakarta.transaction.Transactional;

@Service
public class BookingService {

	private final KafkaService kafkaService;
	final BookingRepository repository;
	final PaymentRepository paymentRepository;

	public BookingService(BookingRepository bookingRepository, PaymentRepository paymentRepository, KafkaService kafkaService) {
		this.repository = bookingRepository;
		this.paymentRepository = paymentRepository;
		this.kafkaService = kafkaService;
	}

	@Transactional
	public BookingResponse doBooking(BookingRequest request) {

		BookingEntity bookingEntity = new BookingEntity();
		bookingEntity.setFromStation(request.getForm());
		bookingEntity.setToStation(request.getTo());
		bookingEntity.setPassengerName(request.getPassengerName());
		bookingEntity.setAge(request.getAge());
		bookingEntity.setGender(request.getGender());
		bookingEntity.setJourneyDate(request.getDate());
		bookingEntity.setUserId(request.getUserId());
		bookingEntity.setStatus("Booking_INIT");
		bookingEntity.setTravelClass(request.getTravelClass());

		// 1st save
		bookingEntity = repository.save(bookingEntity);

		PaymentEntity paymentEntity = new PaymentEntity();
		paymentEntity.setBookingId(bookingEntity.getBookingId());
		paymentEntity.setAmount(1245.34);
		paymentEntity.setTranscationId("TXN12412");
		paymentEntity.setPaymentStatus("FAILED");

//		try {
//			String statsuFromPG = null; /// Intentional failing the payment to see the roll_back
//			paymentEntity.setPaymentStatus(statsuFromPG.concat("some text..."));
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new InsufficientBanlanceException("User does not have enough balance to book.");
//		}

		// 2nd save
		PaymentEntity paymentEntityResponse = paymentRepository.save(paymentEntity);

		BookingResponse response = null;
		if (paymentEntityResponse.getPaymentId() > 0) {
			bookingEntity.setPnr(generatePnr());
			bookingEntity.setStatus("Booked");

			// 3rd save
			BookingEntity bookingEntityUpdated = repository.save(bookingEntity);

			response = new BookingResponse();
			response.setBookingId(bookingEntityUpdated.getBookingId());
			response.setPnrNumber(bookingEntityUpdated.getPnr());
			response.setBookingStatus("CONFIRMED"); // Or WAITING, RAC, etc.
			response.setJourneyDate(bookingEntityUpdated.getJourneyDate());
			response.setCoach("B2");
			response.setSeatNumber("32");
			response.setMessage("Ticket booked successfully.");

		}
		
		for(int i=0; i<500 ; i++) {
			
			String message = "This is test message and pnr is " + response.getPnrNumber();
			kafkaService.publishMessage("booking-confirmed", message);
			System.out.println("Event published to kafka topic.....");
			
		}
		

		return response;
	}

	public static String generatePnr() {
		Random random = new Random();
		long pnr = 1000000000L + (long) (random.nextDouble() * 9000000000L);
		return String.valueOf(pnr);
	}

}
