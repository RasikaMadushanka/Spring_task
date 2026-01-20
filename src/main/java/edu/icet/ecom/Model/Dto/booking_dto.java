package edu.icet.ecom.Model.Dto;

import edu.icet.ecom.Model.Entity.seat_entity;
import edu.icet.ecom.Model.Entity.user_entity;

import java.math.BigDecimal;

public class booking_dto {
    private Long id;
    private user_entity user;
    private seat_entity seat;

    private BigDecimal amountPaid;

    // Using String for status (e.g., "CONFIRMED", "CANCELLED")
    private String status;
}
