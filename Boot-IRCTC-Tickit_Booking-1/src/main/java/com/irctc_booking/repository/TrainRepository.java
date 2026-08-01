package com.irctc_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.irctc_booking.entity.TrainEntity;

import jakarta.persistence.LockModeType;

import java.util.Optional;


@Repository
public interface TrainRepository extends JpaRepository<TrainEntity, Long>{

	
	Optional<TrainEntity> findByTrainNumber(String trainNumber);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT t FROM TrainEntity t WHERE t.trainNumber = :trainNumber")
 	Optional<TrainEntity>  findByTrainNumberForUpdate(@Param("trainNumber") String trainNumber);
}
