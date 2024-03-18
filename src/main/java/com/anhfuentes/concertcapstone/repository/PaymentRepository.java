package com.anhfuentes.concertcapstone.repository;

import com.anhfuentes.concertcapstone.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository <Payment, Long> {
    List<Payment> findTop10ByOrderByPaymentDateDesc();
    Optional<Payment> findByBookingId(Long bookingId);
}
