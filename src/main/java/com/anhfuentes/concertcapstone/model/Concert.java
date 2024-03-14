package com.anhfuentes.concertcapstone.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name="concert")
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDateTime dateTime;
    private String venue;

    @OneToMany(mappedBy = "concert")
    private List<Booking> bookings;
}
