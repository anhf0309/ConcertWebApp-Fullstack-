package com.anhfuentes.concertcapstone.service;


import com.anhfuentes.concertcapstone.model.Arena;
import java.util.List;
public interface ArenaService {
    Arena addArena(Arena arena);

    Arena updateArena(Arena arena);

    List<Arena> getAllArenas();

    Arena getArenaById(Long id);

    Arena getArenaByLocation(String location);

    Arena getArenaByName(String arena_name);
}
