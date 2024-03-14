package com.glab.concertcapstone.repository;

import com.glab.concertcapstone.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository <Booking, Long> {
}
