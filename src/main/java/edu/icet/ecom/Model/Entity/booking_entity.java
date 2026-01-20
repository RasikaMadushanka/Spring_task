package edu.icet.ecom.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "bookings")
public class booking_entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relationship: Many bookings can belong to one user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private user_entity user;

    // Relationship: One booking corresponds to one specific seat
    @OneToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private seat_entity seat;

    private BigDecimal amountPaid;

    // Using String for status (e.g., "CONFIRMED", "CANCELLED")
    private String status;
}
