package edu.icet.ecom.Service;

import edu.icet.ecom.Model.Dto.seat_dto;
import edu.icet.ecom.Model.Entity.event_entity;
import edu.icet.ecom.Model.Entity.seat_entity;
import edu.icet.ecom.Repository.event_repository; // Ensure this is imported
import edu.icet.ecom.Repository.seat_repository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class seat_service {
    @Autowired
    seat_repository seatRepository;

    @Autowired
    event_repository eventRepository;
    ModelMapper modelMapper = new ModelMapper();



    // Inside seat_service class
    @Scheduled(fixedRate = 60000) // Runs every 60 seconds
    @Transactional
    public void releaseExpiredSeats() {
        LocalDateTime now = LocalDateTime.now();
        List<seat_entity> expiredSeats = seatRepository.findByStatusAndHoldExpiryBefore("HELD", now);

        if (!expiredSeats.isEmpty()) {
            for (seat_entity seat : expiredSeats) {
                seat.setStatus("AVAILABLE");
                seat.setHoldExpiry(null); 
            }
            seatRepository.saveAll(expiredSeats);
            System.out.println("⏰ Scheduled Task: Released " + expiredSeats.size() + " expired seats.");
        }
    }

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
                throw new RuntimeException("Seat is locked for " + remainingSeconds + " seconds");
            }

            System.out.println("Hold expired for seat " + id + ". Re-holding now.");
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