package com.irctc_booking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irctc_booking.entity.BookingEntity;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long>{

	Optional<BookingEntity> findByPnr(String pnr);
}
