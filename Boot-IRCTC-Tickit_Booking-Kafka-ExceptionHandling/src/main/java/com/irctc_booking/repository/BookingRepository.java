package com.irctc_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irctc_booking.entity.BookingEntity;
import java.util.List;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

	List<BookingEntity> findByUserId(String userId);
}
