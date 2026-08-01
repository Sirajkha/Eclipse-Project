package com.irctc_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irctc_booking.entity.StationEntity;

import java.util.Optional;

@Repository
public interface StationRepository extends JpaRepository<StationEntity, Long> {

	Optional<StationEntity> findByCode(String code);
}
