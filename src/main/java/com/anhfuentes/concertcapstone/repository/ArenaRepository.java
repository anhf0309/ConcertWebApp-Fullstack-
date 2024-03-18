package com.anhfuentes.concertcapstone.repository;

import com.anhfuentes.concertcapstone.model.Arena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArenaRepository extends JpaRepository <Arena, Long> {
    Arena findArenaByLocation (String location);

    Arena findArenaByName(String arena_name);
}
