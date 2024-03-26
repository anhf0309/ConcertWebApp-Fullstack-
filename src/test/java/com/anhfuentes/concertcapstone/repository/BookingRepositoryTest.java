package com.anhfuentes.concertcapstone.repository;

import com.anhfuentes.concertcapstone.model.Booking;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DataJpaTest
public class BookingRepositoryTest {

    @MockBean
    private BookingRepository bookingRepository;

    @Test
    public void testFindByUserId() {
        Long userId = 1L;
        when(bookingRepository.findByUserId(userId)).thenReturn(List.of(new Booking()));

        List<Booking> bookings = bookingRepository.findByUserId(userId);
        assertThat(bookings).isNotEmpty(); // Verify the result

        verify(bookingRepository, times(1)).findByUserId(userId);
    }

    @Test
    public void testFindByConcertId() {
        Long concertId = 1L;
        when(bookingRepository.findByConcertId(concertId)).thenReturn(List.of(new Booking()));

        List<Booking> bookings = bookingRepository.findByConcertId(concertId);
        assertThat(bookings).isNotEmpty(); // Verify the result

        verify(bookingRepository, times(1)).findByConcertId(concertId);
    }
}
