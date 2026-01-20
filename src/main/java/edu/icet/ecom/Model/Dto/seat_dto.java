package edu.icet.ecom.Model.Dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class seat_dto {
    private Long id;
    private String status;
    private LocalDateTime holdExpiry;
    private Long version;
}
