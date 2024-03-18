package com.anhfuentes.concertcapstone.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1)
    private Long id;

    @NotBlank(message = "First name cannot be empty")
    @Column(name="firstname")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Column(name="lastname")
    private String lastName;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Column(name= "password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> bookings;
}
