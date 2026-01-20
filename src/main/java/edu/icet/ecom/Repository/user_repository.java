package edu.icet.ecom.Repository;

import edu.icet.ecom.Model.Entity.user_entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface user_repository extends JpaRepository<user_entity, Long> {
}
