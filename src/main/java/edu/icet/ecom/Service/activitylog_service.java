package edu.icet.ecom.Service;

import edu.icet.ecom.Model.Entity.activitylog_entity;
import edu.icet.ecom.Repository.activitylog_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class activitylog_service {

    @Autowired
    private activitylog_repository activityLogRepository;

    public List<activitylog_entity> getAllLogs() {
        return activityLogRepository.findAll();
    }
}