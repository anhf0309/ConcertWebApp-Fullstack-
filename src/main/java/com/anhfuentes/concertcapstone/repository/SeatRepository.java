package com.anhfuentes.concertcapstone.repository;

import com.anhfuentes.concertcapstone.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByConcertIdAndIsBookedFalse(Long concertId);
    List<Seat> findByConcertId(Long concertId);
    @Query("SELECT COUNT(s) FROM seat s WHERE s.concert.id = :concertId AND s.isBooked = false")
    Long countAvailableSeatsByConcert(@Param("concertId") Long concertId);

}