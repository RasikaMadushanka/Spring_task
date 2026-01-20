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

        private String status;
        private LocalDateTime holdExpiry;

        @Version
        private Long version;

        // ADD THIS RELATIONSHIP
        @ManyToOne
        @JoinColumn(name = "event_id")
        private event_entity event;
    }

