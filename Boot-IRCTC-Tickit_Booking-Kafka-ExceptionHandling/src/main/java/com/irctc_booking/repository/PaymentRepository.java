package com.irctc_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irctc_booking.entity.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

}
