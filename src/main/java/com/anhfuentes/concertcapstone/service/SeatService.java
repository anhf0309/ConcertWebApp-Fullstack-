package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.model.Seat;
import java.util.List;

public interface SeatService {

    Seat bookSeat(Long seatId);
    boolean releaseSeat(Long seatId);
    List<Seat> getSeatsByConcert(Long concertId);
    List<Seat> getAvailableSeatsByConcert(Long concertId);
    void initializeSeatsForConcert(Long concertId);
}
