package edu.icet.ecom.Repository;

import edu.icet.ecom.Model.Entity.activitylog_entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface activitylog_repository extends JpaRepository<activitylog_entity, Long> {
}
