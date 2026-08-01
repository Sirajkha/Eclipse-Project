package com.irctc_booking.entity;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "train_route")
public class TrainRouteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "train_route_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "train_id")
	private TrainEntity trainEntity;

	@ManyToOne
	@JoinColumn(name = "station_id")
	private StationEntity stationEntity;

	private int stopSequence;
	private LocalTime arrivalTime;
	private LocalTime departureTime;

	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TrainEntity getTrainEntity() {
		return trainEntity;
	}

	public void setTrainEntity(TrainEntity trainEntity) {
		this.trainEntity = trainEntity;
	}

	public StationEntity getStationEntity() {
		return stationEntity;
	}

	public void setStationEntity(StationEntity stationEntity) {
		this.stationEntity = stationEntity;
	}

	public int getStopSequence() {
		return stopSequence;
	}

	public void setStopSequence(int stopSequence) {
		this.stopSequence = stopSequence;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

}
