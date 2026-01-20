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
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private user_entity user;

    @OneToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private seat_entity seat;

    private BigDecimal amountPaid;
    private String status;
}
