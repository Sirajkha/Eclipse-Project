package com.irctc_booking.service;

import java.util.List;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.irctc_booking.entity.BookingEntity;
import com.irctc_booking.entity.BookingStatus;
import com.irctc_booking.entity.PassengerEntity;
import com.irctc_booking.entity.PassengerStatus;
import com.irctc_booking.entity.StationEntity;
import com.irctc_booking.entity.TrainEntity;
import com.irctc_booking.repository.BookingRepository;
import com.irctc_booking.repository.PassengerRepository;
import com.irctc_booking.repository.StationRepository;
import com.irctc_booking.repository.TrainRepository;
import com.irctc_booking.repository.TrainRouteRepository;
import com.irctc_booking.request.BookingRequest;
import com.irctc_booking.request.PassengerRequest;
@Service
public class BookingService {

	final TrainRepository trainRepository;
	final StationRepository stationRepository;
	final TrainRouteRepository routeRepository;
	final PassengerRepository passengerRepository;
	final BookingRepository bookingRepository;

	public BookingService(TrainRepository trainRepository, TrainRouteRepository routeRepository,
			StationRepository stationRepository, PassengerRepository passengerRepository,
			BookingRepository bookingRepository) {
		this.trainRepository = trainRepository;
		this.routeRepository = routeRepository;
		this.stationRepository = stationRepository;
		this.passengerRepository = passengerRepository;
		this.bookingRepository = bookingRepository;
	}

	@Transactional
	public String createBooking(BookingRequest request) {

//		TrainEntity trainEntity = trainRepository.findByTrainNumber(request.getTrainNumber())
//				.orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Train  Not Found")); // not thread safe
		TrainEntity trainEntity = trainRepository.findByTrainNumberForUpdate(request.getTrainNumber())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Train Not Found"));  // th read safe
		StationEntity stationEntitySrc = stationRepository.findByCode(request.getSourceCode()).orElseThrow(
				() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Source Station Not Found"));
		StationEntity stationEntityDst = stationRepository.findByCode(request.getDestinationCode()).orElseThrow(
				() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Destionation Station Not Found"));

		Long bookingCount = passengerRepository.countAllPassengers(trainEntity, request.getJourneyDate(),
				PassengerStatus.CONFIRMED);
		int availableSeat = trainEntity.getTotalSeats() - bookingCount.intValue();

		if (request.getPassengerRequests().size() > availableSeat) {
			throw new ResponseStatusException(HttpStatusCode.valueOf(409), "Not available seats");
		}

		BookingEntity bookingEntity = new BookingEntity();
		bookingEntity.setPnr(generatePnr());
		bookingEntity.setSourceStation(stationEntitySrc);
		bookingEntity.setDestinationStation(stationEntityDst);
		bookingEntity.setTrainEntity(trainEntity);
		bookingEntity.setJourneyDate(request.getJourneyDate());
		bookingEntity.setBookingStatus(BookingStatus.CONFIRMED);

		BookingEntity savedBooking = bookingRepository.save(bookingEntity);

		int seatCount = bookingCount.intValue(); // continue numbering from where booked seats left off

		for (PassengerRequest psngr : request.getPassengerRequests()) {
			PassengerEntity passengerEntity = new PassengerEntity();
			passengerEntity.setPassengerName(psngr.getName());
			passengerEntity.setAge(psngr.getAge());
			passengerEntity.setGender(psngr.getGender());
			passengerEntity.setBooking(savedBooking);
			seatCount++;
			passengerEntity.setSeatNumber(seatCount);
			passengerEntity.setPassengerStatus(PassengerStatus.CONFIRMED);
			passengerRepository.save(passengerEntity);
		}

		return savedBooking.getPnr();
	}

	public static String generatePnr() {
		Random random = new Random();
		long pnr = 1000000000L + (long) (random.nextDouble() * 9000000000L);
		return String.valueOf(pnr);
	}

	
	
	@Transactional
	public String cancelBooking(String pnr) {

		BookingEntity bookingEntity = bookingRepository.findByPnr(pnr)
				.orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "PNR Not Found."));

		List<PassengerEntity> passengerEntities = passengerRepository.findByBooking(bookingEntity);

		for (PassengerEntity ps : passengerEntities) {
			ps.setPassengerStatus(PassengerStatus.CANCELLED);
			passengerRepository.save(ps);
		}

		bookingEntity.setBookingStatus(BookingStatus.CANCELLED);
		bookingRepository.save(bookingEntity);

		return "Successfully Cancelled.";
	}
}
