package com.glab.concertcapstone.repository;

import com.glab.concertcapstone.model.Arena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArenaRepository extends JpaRepository <Arena, Long> {
}
