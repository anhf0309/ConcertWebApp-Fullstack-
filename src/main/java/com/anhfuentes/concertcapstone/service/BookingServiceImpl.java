package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.model.Booking;
import com.anhfuentes.concertcapstone.repository.BookingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Booking booking) {
        Long bookingId = booking.getId();
        if(bookingId != null && bookingRepository.existsById(bookingId)) {
            return bookingRepository.save(booking);
        } else {
            throw new EntityNotFoundException("Booking Not Found with id: " + bookingId);
        }
    }

    @Override
    public boolean cancelBooking(Long bookingId) {
        bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with id: " + bookingId));
        bookingRepository.deleteById(bookingId);
        return true;
    }

    @Override
    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserId(userId);
    }


    @Override
    public List<Booking> getBookingsForConcert(Long concertId) {
        return bookingRepository.findByConcertId(concertId);
    }

    @Override
    public Optional<Booking> getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

}
