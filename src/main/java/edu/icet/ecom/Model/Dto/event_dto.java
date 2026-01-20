package edu.icet.ecom.Model.Dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class event_dto {
    private Long id;

    private String name;
    private BigDecimal basePrice;
    private boolean isHighDemand;
    private LocalDateTime eventDate;
}
