package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.model.Payment;
import com.anhfuentes.concertcapstone.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static com.anhfuentes.concertcapstone.enums.PaymentStatus.Refunded;

@Service
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;

    @Autowired
    PaymentServiceImpl(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }
    @Override
    public Payment processPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public boolean refundPayment(Long paymentId) {
        Optional<Payment> paymentOptional = paymentRepository.findById(paymentId);
        if(paymentOptional.isPresent()) {
            Payment payment = paymentOptional.get();
            payment.setStatus(Refunded);
            paymentRepository.save(payment);
            return true;
        }
        return false;
    }

    @Override
    public Payment getPaymentByBooking(Long bookingId) {
        return paymentRepository.findByBookingId(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found for booking ID: " + bookingId));
    }

    @Override
    public List<Payment> getRecentPayments() {
        return paymentRepository.findTop10ByOrderByPaymentDateDesc();
    }
}
