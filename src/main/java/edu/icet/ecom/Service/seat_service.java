package edu.icet.ecom.Service;

import edu.icet.ecom.Model.Dto.seat_dto;
import edu.icet.ecom.Model.Entity.seat_entity;
import edu.icet.ecom.Repository.seat_repository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;

import java.time.Duration;
import java.time.LocalDateTime;

public class seat_service {
    @Autowired
    seat_repository seatRepository;
    ModelMapper modelMapper = new ModelMapper();
    @Transactional
    public seat_dto holdSeat(Long id) {

            // 1. Find the seat
            seat_entity seat = seatRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Seat not found"));

            LocalDateTime now = LocalDateTime.now();

            // 2. CHECK: If seat is SOLD, it cannot be held
            if ("SOLD".equals(seat.getStatus())) {
                throw new RuntimeException("Seat is already sold");
            }

            // 3. CHECK: If seat is HELD, check the timestamp
            if ("HELD".equals(seat.getStatus())) {
                // Check if hold is still active (within 10 mins)
                if (seat.getHoldExpiry() != null && seat.getHoldExpiry().isAfter(now)) {
                    // Calculate remaining seconds for the Exception
                    long remainingSeconds = Duration.between(now, seat.getHoldExpiry()).getSeconds();
                    throw new SeatLockedException(remainingSeconds);
                }
            }

            // 4. OVERWRITE/CREATE HOLD: Status is AVAILABLE or HOLD is EXPIRED
            seat.setStatus("HELD");
            seat.setHoldExpiry(now.plusMinutes(10));

            // 5. SAVE: @Version in seat_entity handles the Concurrency Challenge
            seat_entity savedSeat = seatRepository.save(seat);

            return modelMapper.map(savedSeat, seat_dto.class);
        }
    }

