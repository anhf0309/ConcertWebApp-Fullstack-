package com.anhfuentes.concertcapstone.repository;

import com.anhfuentes.concertcapstone.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByArenaIdAndIsBookedFalse(Long arenaId);
    List<Seat> findByConcertId(Long concertId);
    @Query("SELECT s.arena, COUNT(s) FROM seat s WHERE s.arena.id = :arenaId AND s.isBooked = false GROUP BY s.arena")
    List<Object[]> countAvailableSeatsByArena(@Param("arenaId") Long arenaId);
}