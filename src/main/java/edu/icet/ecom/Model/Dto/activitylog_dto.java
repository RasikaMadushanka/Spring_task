package edu.icet.ecom.Model.Dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class activitylog_dto {
    private Long id;

    private String action; // e.g., "HOLD_FAILED"

    private Long userId; //
    private String details; // Stores the error message or reason for failure

    private LocalDateTime timestamp;
}

