package com.anhfuentes.concertcapstone.model;

import com.anhfuentes.concertcapstone.enums.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    private double amount;

    @NotNull(message = "Payment date cannot be null")
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)  // Specify how the enum is persisted
    private PaymentStatus status = PaymentStatus.Received;  // Default value

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}

