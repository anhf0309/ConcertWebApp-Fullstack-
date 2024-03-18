package com.anhfuentes.concertcapstone.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;


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
        @NotBlank(message = "Arena name cannot be empty")
        private String name;
        @NotBlank(message = "Location cannot be empty")
        private String location;

        @OneToMany(mappedBy = "arena")
        private List<Seat> seats;

    }

