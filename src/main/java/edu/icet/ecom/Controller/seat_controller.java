package edu.icet.ecom.Controller;

import edu.icet.ecom.Model.Dto.seat_dto;
import edu.icet.ecom.Service.seat_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seat")
public class seat_controller {
    @Autowired
    seat_service seatService;

    @PostMapping("/add")
    public seat_dto addSeat(@RequestBody seat_dto seatDto) {
        return seatService.addSeat(seatDto);
    }

    @PostMapping("/{id}/hold")
    public seat_dto holdSeat(@PathVariable Long id) {
        return seatService.holdSeat(id);
    }
}
