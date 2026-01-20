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
@Table(name = "audit_logs")
public class activitylog_entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action; // e.g., "HOLD_FAILED"

    private Long userId; // The ID of the user who attempted the action

    @Column(columnDefinition = "TEXT")
    private String details; // Stores the error message or reason for failure

    private LocalDateTime timestamp;
}
