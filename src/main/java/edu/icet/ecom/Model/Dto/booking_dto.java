package edu.icet.ecom.Model.Dto;

import edu.icet.ecom.Model.Entity.seat_entity;
import edu.icet.ecom.Model.Entity.user_entity;
import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class booking_dto {
    private Long id;
    private user_entity user;
    private seat_entity seat;

    private BigDecimal amountPaid;
    private boolean priorityAccess;
    private String status;
}
