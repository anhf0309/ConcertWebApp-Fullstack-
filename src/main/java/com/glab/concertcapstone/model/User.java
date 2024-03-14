package com.glab.concertcapstone.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Getter
@Setter
    @Entity(name = "user")
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name="username")
        private String username;
        @Column(name = "email")
        private String email;
        @Column(name= "password")
        private String password;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        private List<Booking> bookings;
}
