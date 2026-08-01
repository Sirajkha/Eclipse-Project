package com.irctc_booking.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking")
public class BookingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private Long id;
	
	private String pnr;
	
	@ManyToOne
	@JoinColumn(name = "train_id")
	private TrainEntity trainEntity;
	
	@ManyToOne
	@JoinColumn(name = "source_station_id")
	private StationEntity sourceStation;
	
	@ManyToOne
	@JoinColumn(name = "destination_station_id")
	private StationEntity destinationStation;
	
	private LocalDate journeyDate;
	
	@Enumerated(EnumType.STRING)
	private BookingStatus bookingStatus;

	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public TrainEntity getTrainEntity() {
		return trainEntity;
	}

	public void setTrainEntity(TrainEntity trainEntity) {
		this.trainEntity = trainEntity;
	}

	
	public StationEntity getSourceStation() {
		return sourceStation;
	}

	public void setSourceStation(StationEntity sourceStation) {
		this.sourceStation = sourceStation;
	}

	public StationEntity getDestinationStation() {
		return destinationStation;
	}

	public void setDestinationStation(StationEntity destinationStation) {
		this.destinationStation = destinationStation;
	}

	public LocalDate getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(LocalDate journeyDate) {
		this.journeyDate = journeyDate;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	
	
	
	
	
}
