package com.anhfuentes.concertcapstone.repository;

import com.anhfuentes.concertcapstone.model.Concert;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ConcertRepository extends JpaRepository <Concert, Long> {
    List<Concert> findByDateTimeAfter (LocalDateTime date_time);
    List<Concert> findByGenre(String genre, Sort sort);
}

