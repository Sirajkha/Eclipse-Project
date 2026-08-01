package com.irctc_booking.request;

import java.time.LocalDate;
import java.util.List;

public class BookingRequest {

	private String trainNumber;
	private String sourceCode;
	private String destinationCode;
	private LocalDate journeyDate;
	private List<PassengerRequest> passengerRequests;

	public String getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getDestinationCode() {
		return destinationCode;
	}

	public void setDestinationCode(String destinationCode) {
		this.destinationCode = destinationCode;
	}

	public LocalDate getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(LocalDate journeyDate) {
		this.journeyDate = journeyDate;
	}

	public List<PassengerRequest> getPassengerRequests() {
		return passengerRequests;
	}

	public void setPassengerRequests(List<PassengerRequest> passengerRequests) {
		this.passengerRequests = passengerRequests;
	}

}
