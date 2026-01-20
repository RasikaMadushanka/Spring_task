package edu.icet.ecom.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "seats")
public class seat_entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Remove @Enumerated(EnumType.STRING) when using String type
    @Column(name = "status")
    private String status; // Stores "AVAILABLE", "HELD", or "SOLD"

    // Other fields...
    private LocalDateTime holdExpiry;

    @Version
    private Long version;
}
