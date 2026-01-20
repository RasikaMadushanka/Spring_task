package edu.icet.ecom.Model.Dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class event_dto {
    private Long id;

    private String name;
    private BigDecimal basePrice;
    private boolean isHighDemand;
    private LocalDateTime eventDate;
}
