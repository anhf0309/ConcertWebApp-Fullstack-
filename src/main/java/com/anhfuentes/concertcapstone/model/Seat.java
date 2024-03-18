package com.anhfuentes.concertcapstone.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Seat number cannot be empty")
    private String seatNumber;
    private boolean isBooked;

    @ManyToOne
    @JoinColumn(name = "arena_id")
    private Arena arena;

}
