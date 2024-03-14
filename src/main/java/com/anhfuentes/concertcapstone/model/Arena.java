package com.anhfuentes.concertcapstone.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "arena")
public class Arena {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private String location;

        @OneToMany(mappedBy = "arena")
        private List<Seat> seats;

    }

