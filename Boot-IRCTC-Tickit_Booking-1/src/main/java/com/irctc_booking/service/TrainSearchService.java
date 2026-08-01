package com.irctc_booking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.irctc_booking.entity.PassengerStatus;
import com.irctc_booking.entity.TrainEntity;
import com.irctc_booking.entity.TrainRouteEntity;
import com.irctc_booking.repository.PassengerRepository;
import com.irctc_booking.repository.TrainRouteRepository;
import com.irctc_booking.response.TrainSearchResultResponse;

@Service
public class TrainSearchService {

	final PassengerRepository passengerRepository;

	final TrainRouteRepository routeRepository;

	public TrainSearchService(PassengerRepository passengerRepository, TrainRouteRepository routeRepository) {
		this.passengerRepository = passengerRepository;
		this.routeRepository = routeRepository;
	}

	public List<TrainSearchResultResponse> searchTrains(String sourceCode, String destinationCode,
			LocalDate journeyDate) {
		List<Object[]> objects = routeRepository.searchTrains(sourceCode, destinationCode);
		List<TrainSearchResultResponse> listResponses = new ArrayList<TrainSearchResultResponse>();

		for (Object[] row : objects) {
			TrainRouteEntity src = (TrainRouteEntity) row[0];
			TrainRouteEntity dst = (TrainRouteEntity) row[1];
			TrainEntity train = src.getTrainEntity();

			Long bookedCound = passengerRepository.countAllPassengers(train, journeyDate, PassengerStatus.CONFIRMED);
			int availableSeats = train.getTotalSeats() - bookedCound.intValue();

			TrainSearchResultResponse response = new TrainSearchResultResponse(train.getTrainNumber(), train.getName(),
					src.getDepartureTime(), dst.getArrivalTime(), src.getStationEntity().getName(), dst.getStationEntity().getName(), availableSeats);
			listResponses.add(response);
		}

		return listResponses;
	}
}
