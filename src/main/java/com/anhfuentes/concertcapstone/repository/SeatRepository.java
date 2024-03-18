package com.anhfuentes.concertcapstone.repository;

import com.anhfuentes.concertcapstone.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByArenaIdAndIsBookedFalse(Long arenaId);
}