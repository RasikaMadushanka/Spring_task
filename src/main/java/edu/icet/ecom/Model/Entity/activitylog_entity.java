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

    private String action;

    private Long userId;

    @Column(columnDefinition = "TEXT")
    private String details;

    private LocalDateTime timestamp;
}
