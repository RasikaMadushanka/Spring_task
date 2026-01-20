package edu.icet.ecom.Controller;

import edu.icet.ecom.Model.Entity.activitylog_entity;
import edu.icet.ecom.Service.activitylog_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audit")
public class activitylog_controller {

    @Autowired
    private activitylog_service activityLogService;

    @GetMapping("/view-logs")
    public List<activitylog_entity> viewAllLogs() {
        return activityLogService.getAllLogs();
    }
}