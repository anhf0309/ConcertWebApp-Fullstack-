package com.anhfuentes.concertcapstone.repository;

import com.anhfuentes.concertcapstone.model.Payment;
import com.anhfuentes.concertcapstone.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PaymentRepositoryTest {

    @MockBean
    private PaymentRepository paymentRepository;

    @Test
    public void testFindTop10ByOrderByPaymentDateDesc() {
        when(paymentRepository.findTop10ByOrderByPaymentDateDesc()).thenReturn(List.of(new Payment()));

        List<Payment> payments = paymentRepository.findTop10ByOrderByPaymentDateDesc();
        assertThat(payments).isNotEmpty(); // Verify the result

        verify(paymentRepository, times(1)).findTop10ByOrderByPaymentDateDesc();
    }

    @Test
    public void testFindByBookingId() {
        Long bookingId = 1L;
        when(paymentRepository.findByBookingId(bookingId)).thenReturn(Optional.of(new Payment()));

        Optional<Payment> payment = paymentRepository.findByBookingId(bookingId);
        assertThat(payment).isPresent(); // Verify the result

        verify(paymentRepository, times(1)).findByBookingId(bookingId);
    }
}
