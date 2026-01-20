package edu.icet.ecom.Controller;

import edu.icet.ecom.Model.Dto.seat_dto;
import edu.icet.ecom.Service.seat_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seat")
public class seat_controller {
    @Autowired
    seat_service seatService;

    @PostMapping("/{id}/hold")
    public seat_dto holdSeat(@PathVariable Long id) {
        return seatService.holdSeat(id);
    }
}
