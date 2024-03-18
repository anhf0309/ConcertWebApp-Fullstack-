package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.model.Payment;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface PaymentService {
   Payment processPayment(Payment payment);
   boolean refundPayment(Long paymentId);
   Payment getPaymentByBooking(Long bookingId);
   List<Payment> getRecentPayments();

}
