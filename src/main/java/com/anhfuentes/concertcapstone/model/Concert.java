package com.anhfuentes.concertcapstone.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="concert")
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Concert name cannot be blank")
    private String name;

    @NotBlank(message = "Artist name cannot be blank")
    private String artist;

    @NotNull(message = "Concert date and time cannot be null")
    private LocalDateTime dateTime;

    @Lob
    @Column(name = "poster_image", columnDefinition = "LONGBLOB")
    private byte[] posterImage;

    public Concert(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "concert", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();

}
