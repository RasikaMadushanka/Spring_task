package edu.icet.ecom.Repository;

import edu.icet.ecom.Model.Entity.seat_entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface seat_repository extends JpaRepository<seat_entity, Long> {
    List<seat_entity> findByStatusAndHoldExpiryBefore(String status, LocalDateTime dateTime);
}
