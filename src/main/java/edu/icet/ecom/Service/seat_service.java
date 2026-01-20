package edu.icet.ecom.Service;

import edu.icet.ecom.Model.Dto.seat_dto;
import edu.icet.ecom.Model.Entity.event_entity;
import edu.icet.ecom.Model.Entity.seat_entity;
import edu.icet.ecom.Repository.event_repository; // Ensure this is imported
import edu.icet.ecom.Repository.seat_repository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class seat_service {
    @Autowired
    seat_repository seatRepository;

    @Autowired
    event_repository eventRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public seat_dto holdSeat(Long id) {
        seat_entity seat = seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        LocalDateTime now = LocalDateTime.now();

        if ("SOLD".equals(seat.getStatus())) {
            throw new RuntimeException("Seat is already sold");
        }

        if ("HELD".equals(seat.getStatus())) {
            if (seat.getHoldExpiry() != null && seat.getHoldExpiry().isAfter(now)) {
                long remainingSeconds = Duration.between(now, seat.getHoldExpiry()).getSeconds();
                // Ensure you have created this Custom Exception class
                throw new RuntimeException("Seat is locked for " + remainingSeconds + " seconds");
            }
        }

        seat.setStatus("HELD");
        seat.setHoldExpiry(now.plusMinutes(10));
        seat_entity savedSeat = seatRepository.save(seat);

        return modelMapper.map(savedSeat, seat_dto.class);
    }

    public seat_dto addSeat(seat_dto seatDto) {
        seat_entity entity = modelMapper.map(seatDto, seat_entity.class);
        if (seatDto.getEventId() != null) {
            event_entity event = eventRepository.findById(seatDto.getEventId())
                    .orElseThrow(() -> new RuntimeException("Event ID " + seatDto.getEventId() + " not found"));
            entity.setEvent(event);
        }
        if (entity.getStatus() == null) {
            entity.setStatus("AVAILABLE");
        }
        seat_entity savedSeat = seatRepository.save(entity);
        return modelMapper.map(savedSeat, seat_dto.class);
    }
}