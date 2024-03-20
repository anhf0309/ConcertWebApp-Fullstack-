package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.model.Concert;
import com.anhfuentes.concertcapstone.repository.ConcertRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcertServiceImpl implements ConcertService {

    private final ConcertRepository concertRepository;
    private final SeatService seatService;

    @Autowired
    ConcertServiceImpl(ConcertRepository concertRepository, SeatService seatService) {
        this.concertRepository = concertRepository;
        this.seatService = seatService;
    }
    @Override
    public Concert createConcert(Concert concert) {
        Concert savedConcert = concertRepository.save(concert);
        seatService.initializeSeatsForConcert(savedConcert.getId());
        return savedConcert;
    }

    @Override
    public Concert updateConcert(Concert concert) {
        Long concertId = concert.getId();
        if (concertId != null && concertRepository.existsById(concertId)) {
            return concertRepository.save(concert);
        } else {
            throw new EntityNotFoundException("Concert not found with id: " + concertId);
        }
    }

    @Override
    public Concert getConcertById(Long id) {
        return concertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Concert not found with id: " + id));
    }

    @Override
    public List<Concert> getAllConcerts() {
        return concertRepository.findAll();
    }

    @Override
    public boolean deleteConcert(Long concertId) {
        if(concertRepository.existsById(concertId)) {
            concertRepository.deleteById(concertId);
            return true;
        }
        return false;
    }
}
