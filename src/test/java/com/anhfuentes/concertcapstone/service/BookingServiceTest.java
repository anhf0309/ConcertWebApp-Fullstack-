package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.model.Booking;
import com.anhfuentes.concertcapstone.repository.BookingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Test
    public void cancelBooking_WhenBookingExists_ShouldReturnTrue() {

        Long bookingId = 1L;
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(new Booking()));

        boolean result = bookingService.cancelBooking(bookingId);

        verify(bookingRepository).deleteById(bookingId);
        assertThat(result).isTrue();
    }

    @Test
    public void cancelBooking_WhenBookingDoesNotExist_ShouldThrowEntityNotFoundException() {
        Long bookingId = 1L;
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> bookingService.cancelBooking(bookingId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Booking not found with id: " + bookingId);

        verify(bookingRepository, never()).deleteById(bookingId);
    }
}