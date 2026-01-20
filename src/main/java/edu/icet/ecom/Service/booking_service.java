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
        // 1. Fetch User
        user_entity user = userRepository.findById(bookingDto.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Fetch Seat (which links to the Event)
        seat_entity seat = seatRepository.findById(bookingDto.getSeat().getId())
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        // 3. Get the Event from the Seat to access basePrice and demand status
        var event = seat.getEvent();
        if (event == null) {
            throw new RuntimeException("Seat is not linked to an event. Price cannot be calculated.");
        }

        // 4. Validation: Check if it's already sold
        if ("SOLD".equals(seat.getStatus())) {
            throw new RuntimeException("This seat is already sold!");
        }

        // 5. THE PRICING ENGINE LOGIC (Requirement 1 & 2)
        double basePrice = event.getBasePrice().doubleValue();
        double finalPrice = basePrice; // Default for Regular and Platinum

        String userTier = (user.getTier() != null) ? user.getTier().toUpperCase() : "REGULAR";

        if ("VIP".equals(userTier)) {
            // VIP Rule: 10% off UNLESS High Demand
            if (!event.isHighDemand()) {
                finalPrice = basePrice * 0.9;
            }
        }
        seat.setStatus("SOLD");
        seatRepository.save(seat);

        // 7. Create and Save Booking
        booking_entity booking = new booking_entity();
        booking.setUser(user);
        booking.setSeat(seat);
        booking.setAmountPaid(BigDecimal.valueOf(finalPrice));
        booking.setStatus("CONFIRMED");

        booking_entity savedBooking = bookingRepository.save(booking);

        // 8. Handle DTO Response
        booking_dto responseDto = modelMapper.map(savedBooking, booking_dto.class);

        // Requirement: Platinum User gets "Priority Access" flag
        if ("PLATINUM".equals(userTier)) {
            responseDto.setPriorityAccess(true);
        }

        return responseDto;
    }
}