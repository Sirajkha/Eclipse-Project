package com.irctc_booking.response;

import java.time.LocalTime;

public class TrainSearchResultResponse {

	private String trainNumber;
	private String trainName;
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private String sourceStationName;
	private String destinationStationName;
	private int availableSeats;
	
	public TrainSearchResultResponse(String trainNumber, String trainName, LocalTime departureTime,
			LocalTime arrivalTime, String sourceStationName, String destinationStationName, int availableSeats) {
		this.trainNumber = trainNumber;
		this.trainName = trainName;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.sourceStationName = sourceStationName;
		this.destinationStationName = destinationStationName;
		this.availableSeats = availableSeats;
	}

	
	public String getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getSourceStationName() {
		return sourceStationName;
	}

	public void setSourceStationName(String sourceStationName) {
		this.sourceStationName = sourceStationName;
	}

	public String getDestinationStationName() {
		return destinationStationName;
	}

	public void setDestinationStationName(String destinationStationName) {
		this.destinationStationName = destinationStationName;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

}
