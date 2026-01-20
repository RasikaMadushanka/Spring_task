package edu.icet.ecom.Service;

import edu.icet.ecom.Model.Dto.booking_dto;
import edu.icet.ecom.Model.Entity.booking_entity;
import edu.icet.ecom.Model.Entity.seat_entity;
import edu.icet.ecom.Model.Entity.user_entity;
import edu.icet.ecom.Repository.booking_repository;
import edu.icet.ecom.Repository.seat_repository;
import edu.icet.ecom.Repository.user_repository; // Added this import
import edu.icet.ecom.config.AuditFailure;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Important for data integrity

import java.math.BigDecimal;

@Service
public class booking_service {
    @Autowired
    booking_repository bookingRepository;

    @Autowired
    seat_repository seatRepository;

    @Autowired
    user_repository userRepository;

    private final ModelMapper modelMapper = new ModelMapper();
    @AuditFailure
    @Transactional
    public booking_dto createBooking(booking_dto bookingDto) {
        user_entity user = userRepository.findById(bookingDto.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        seat_entity seat = seatRepository.findById(bookingDto.getSeat().getId())
                .orElseThrow(() -> new RuntimeException("Seat not found"));
        var event = seat.getEvent();
        if (event == null) {
            throw new RuntimeException("Seat is not linked to an event. Price cannot be calculated.");
        }
        if ("HELD".equals(seat.getStatus())) {
            if (seat.getHoldExpiry() != null && seat.getHoldExpiry().isBefore(java.time.LocalDateTime.now())) {
                throw new RuntimeException("Your 10-minute hold session has expired. Please hold the seat again.");
            }
        }
        if ("SOLD".equals(seat.getStatus())) {
            throw new RuntimeException("This seat is already sold!");
        }

        double basePrice = event.getBasePrice().doubleValue();
        double finalPrice = basePrice;
        String userTier = (user.getTier() != null) ? user.getTier().toUpperCase() : "REGULAR";
        if ("VIP".equals(userTier)) {
            if (!event.isHighDemand()) {
                finalPrice = basePrice * 0.9;
            }
        }
        seat.setStatus("SOLD");
        seatRepository.save(seat);
        booking_entity booking = new booking_entity();
        booking.setUser(user);
        booking.setSeat(seat);
        booking.setAmountPaid(BigDecimal.valueOf(finalPrice));
        booking.setStatus("CONFIRMED");
        booking_entity savedBooking = bookingRepository.save(booking);
        booking_dto responseDto = modelMapper.map(savedBooking, booking_dto.class);
        if ("PLATINUM".equals(userTier)) {
            responseDto.setPriorityAccess(true);
        }

        return responseDto;
    }
}