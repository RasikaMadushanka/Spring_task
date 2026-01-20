package edu.icet.ecom.Repository;

import edu.icet.ecom.Model.Entity.booking_entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface booking_repository extends JpaRepository<booking_entity, Long> {
}
