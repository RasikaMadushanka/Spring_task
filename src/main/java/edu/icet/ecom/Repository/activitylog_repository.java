package edu.icet.ecom.Repository;

import edu.icet.ecom.Model.Entity.activitylog_entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface activitylog_repository extends JpaRepository<activitylog_entity, Long> {

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    <S extends activitylog_entity> S save(S entity);
}