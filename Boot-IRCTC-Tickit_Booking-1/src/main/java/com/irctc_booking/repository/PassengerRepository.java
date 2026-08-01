package com.irctc_booking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.irctc_booking.entity.BookingEntity;
import com.irctc_booking.entity.PassengerEntity;
import com.irctc_booking.entity.PassengerStatus;
import com.irctc_booking.entity.TrainEntity;

@Repository
public interface PassengerRepository extends JpaRepository<PassengerEntity, Long>{
	
	@Query("SELECT COUNT(p) FROM PassengerEntity p "+ 
	       "WHERE p.booking.journeyDate = :journeyDate "+
		   "AND p.booking.trainEntity = :train "+
		   "AND p.passengerStatus = :status"
	)
	Long countAllPassengers(@Param("train") TrainEntity trainEntity,@Param("journeyDate") LocalDate journeyDate,@Param("status") PassengerStatus status);
    //counts confirmed bookings for seat availability
	
	
	List<PassengerEntity> findByBooking(BookingEntity bookingEntity);
}


// 1. SELECT COUNT(p) FROM PassengerEntity p — counting passenger rows 
// 2. p.booking.journeyDate = :journeyDate — navigated through the relationship and compared properly this time 
// 3. p.booking.trainEntity = :train — comparing the whole entity reference directly, correct spacing 
// 4. p.passengerStatus = :status — direct field, no navigation needed 
// 5. All three @Param names match their query placeholders exactly (train, journeyDate, status) 
// 6. status is typed as PassengerStatus now, matching the entity field 