package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.model.Payment;
import com.anhfuentes.concertcapstone.repository.PaymentRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    public void getRecentPayments_ShouldReturnRecentPayments() {

        List<Payment> expectedPayments = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Payment payment = new Payment();
            expectedPayments.add(payment);
        }

        when(paymentRepository.findTop10ByOrderByPaymentDateDesc()).thenReturn(expectedPayments);

        List<Payment> resultPayments = paymentService.getRecentPayments();
        verify(paymentRepository).findTop10ByOrderByPaymentDateDesc();
        assertThat(resultPayments).hasSize(10);
        assertThat(resultPayments).containsExactlyElementsOf(expectedPayments);
    }
}