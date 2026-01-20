package edu.icet.ecom.Controller;

import edu.icet.ecom.Model.Dto.booking_dto;
import edu.icet.ecom.Service.booking_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class booking_controller {
    @Autowired
    booking_service bookingService;

    @PostMapping("/confirm")
    public booking_dto confirmBooking(@RequestBody booking_dto bookingDto) {
        return bookingService.createBooking(bookingDto);
    }
}
