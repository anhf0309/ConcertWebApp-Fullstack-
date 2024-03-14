package com.glab.concertcapstone.repository;

import com.glab.concertcapstone.model.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertRepository extends JpaRepository <Concert, Long> {
}
