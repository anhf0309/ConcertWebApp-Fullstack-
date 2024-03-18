package com.anhfuentes.concertcapstone.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name="concert")
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Concert name cannot be blank")
    private String name;

    @NotNull(message = "Concert date and time cannot be null")
    private LocalDateTime dateTime;

    @NotBlank(message = "Venue cannot be blank")
    private String venue;

    @OneToMany(mappedBy = "concert")
    private List<Booking> bookings;
}
