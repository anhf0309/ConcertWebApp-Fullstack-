package com.anhfuentes.concertcapstone.repository;

import com.anhfuentes.concertcapstone.model.Arena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArenaRepository extends JpaRepository <Arena, Long> {
    Optional<Arena> findArenaByLocation (String location);

    Optional<Arena> findArenaByName(String arena_name);

    Optional<Arena> findArenaById(Long id);


}
