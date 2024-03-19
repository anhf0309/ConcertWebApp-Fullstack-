package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.model.Booking;
import java.util.List;
import java.util.Optional;

public interface BookingService {
    Booking createBooking(Booking booking);
    Booking updateBooking(Booking booking);
    boolean cancelBooking(Long bookingId);
    List<Booking> getBookingsByUser(Long userId);
    List<Booking> getBookingsForConcert(Long concertId);
    Optional<Booking> getBookingById(Long bookingId);
    List<Booking> getAllBookings();

}
