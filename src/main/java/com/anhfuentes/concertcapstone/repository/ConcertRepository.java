package com.anhfuentes.concertcapstone.repository;

import com.anhfuentes.concertcapstone.model.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertRepository extends JpaRepository <Concert, Long> {

}

