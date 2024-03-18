package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.model.Seat;
import com.anhfuentes.concertcapstone.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public List<Seat> getAvailableSeatsByArena(Long arenaId) {
        return seatRepository.findByArenaIdAndIsBookedFalse(arenaId);
    }

    @Override
    public Seat bookSeat(Long seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found with id: " + seatId));
        if (!seat.isBooked()) {
            seat.setBooked(true);
            return seatRepository.save(seat);
        } else {
            throw new RuntimeException("Seat is already booked.");
        }
    }

    @Override
    public boolean releaseSeat(Long seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found with id: " + seatId));
        if (seat.isBooked()) {
            seat.setBooked(false);
            seatRepository.save(seat);
            return true;
        } else {
            return false; // Seat was not booked to begin with
        }
    }

    @Override
    public List<Seat> getSeatsByConcert(Long concertId) {
        return seatRepository.findByConcertId(concertId);
    }

    @Override
    public List<Object[]> countAvailableSeatsByArena(Long arenaId) {
        return seatRepository.countAvailableSeatsByArena(arenaId);
    }
}
