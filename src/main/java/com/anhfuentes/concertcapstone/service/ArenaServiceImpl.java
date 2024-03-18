package com.anhfuentes.concertcapstone.service;

import com.anhfuentes.concertcapstone.model.Arena;
import com.anhfuentes.concertcapstone.repository.ArenaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArenaServiceImpl implements ArenaService {

    private final ArenaRepository arenaRepository;

    @Autowired
    ArenaServiceImpl(ArenaRepository arenaRepository) {
        this.arenaRepository = arenaRepository;
    }

    @Override
    public Arena addArena(Arena arena) {
        return arenaRepository.save(arena);
    }

    @Override
    public Arena updateArena(Arena arena) {
        Long arenaId = arena.getId();
        if (arenaId != null && arenaRepository.existsById(arenaId)) {
            return arenaRepository.save(arena);
        } else {
            throw new EntityNotFoundException("Arena Not Found with arena ID: " + arena);
        }
    }
    @Override
    public List<Arena> getAllArenas() {
        return arenaRepository.findAll();
    }

    @Override
    public Arena getArenaById(Long id) {
        return arenaRepository.findArenaById(id)
                .orElseThrow(() -> new EntityNotFoundException("Arena Not Found with id: " + id));
    }


    @Override
    public Arena getArenaByLocation(String location) {
        return arenaRepository.findArenaByLocation(location)
                .orElseThrow(() -> new EntityNotFoundException("Arena Not Found in " + location));
    }

    @Override
    public Arena getArenaByName(String arena_name) {
        return arenaRepository.findArenaByName(arena_name)
                .orElseThrow(() -> new EntityNotFoundException("Arena Not Found with the name: " + arena_name));
    }
}
