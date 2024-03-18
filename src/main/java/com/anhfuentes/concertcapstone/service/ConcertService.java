package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.model.Concert;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

public interface ConcertService {
    Concert createConcert(Concert concert);
    Concert updateConcert(Concert concert);
    Concert getConcertById(Long id) throws EntityNotFoundException;
    List<Concert> getAllConcerts();
    boolean deleteConcert(Long concertId);
}
