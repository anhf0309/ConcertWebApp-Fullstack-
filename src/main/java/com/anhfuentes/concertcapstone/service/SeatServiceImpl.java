package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.model.Concert;
import com.anhfuentes.concertcapstone.model.Seat;
import com.anhfuentes.concertcapstone.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private static final int TOTAL_SEATS = 50;

    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
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
            return false;
        }
    }

    @Override
    public List<Seat> getSeatsByConcert(Long concertId) {
        return seatRepository.findByConcertId(concertId);
    }

    @Override
    public List<Seat> getAvailableSeatsByConcert(Long concertId) {
        return seatRepository.findByConcertIdAndIsBookedFalse(concertId);
    }

    @Override
    public void initializeSeatsForConcert(Long concertId) {
        List<Seat> seats = new ArrayList<>();
        for(int i = 1; i <= TOTAL_SEATS; i++) {
            Seat seat = new Seat();
            seat.setConcert(new Concert(concertId));
            seat.setBooked(false);
            seat.setSeatNumber("S" + i);
            seats.add(seat);
        }
        seatRepository.saveAll(seats);
    }

}
