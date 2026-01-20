package edu.icet.ecom.config;

import edu.icet.ecom.Model.Dto.booking_dto;
import edu.icet.ecom.Model.Entity.activitylog_entity;
import edu.icet.ecom.Repository.activitylog_repository;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AuditAspect {

    @Autowired
    private activitylog_repository auditRepository;

    // This method runs only if a method with @AuditFailure throws an Exception
    @AfterThrowing(pointcut = "@annotation(edu.icet.ecom.Config.AuditFailure) && args(bookingDto,..)", throwing = "ex")
    public void logBookingFailure(booking_dto bookingDto, Exception ex) {
        activitylog_entity log = new activitylog_entity();

        log.setAction("BOOKING_FAILED");
        log.setUserId(bookingDto.getUser().getId());
        log.setDetails("Error: " + ex.getMessage());
        log.setTimestamp(LocalDateTime.now());

        auditRepository.save(log);
        System.out.println("⚠️ Audit Shadow: Logged a failure for User ID " + log.getUserId());
    }
}