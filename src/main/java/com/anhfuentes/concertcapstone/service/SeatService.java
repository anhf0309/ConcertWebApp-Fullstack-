package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.model.Seat;
import java.util.List;

public interface SeatService {
    List<Seat> getAvailableSeatsByArena(Long arenaId);
    Seat bookSeat(Long seatId);
    boolean releaseSeat(Long seatId);
    List<Seat> getSeatsByConcert(Long concertId);
    List<Object[]> countAvailableSeatsByArena(Long arenaId);
}
