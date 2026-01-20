package edu.icet.ecom.Repository;

import edu.icet.ecom.Model.Entity.event_entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface event_repository extends JpaRepository<event_entity,Long> {

}
