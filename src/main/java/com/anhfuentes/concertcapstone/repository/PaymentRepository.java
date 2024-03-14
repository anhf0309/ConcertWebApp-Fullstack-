package com.anhfuentes.concertcapstone.repository;

import com.anhfuentes.concertcapstone.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository <Payment, Long> {
}
