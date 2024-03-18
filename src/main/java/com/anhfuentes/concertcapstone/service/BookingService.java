package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.model.Booking;
import java.util.List;

public interface BookingService {
    Booking createBooking(Booking booking);
    boolean cancelBooking(Long bookingId);
    List<Booking> getBookingsByUser(Long userId);
    List<Booking> getBookingsForConcert(Long concertId);
}
