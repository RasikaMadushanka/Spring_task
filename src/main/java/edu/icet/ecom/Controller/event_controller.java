package edu.icet.ecom.Controller;

import edu.icet.ecom.Model.Dto.event_dto;
import edu.icet.ecom.Service.event_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/event")
public class event_controller {
    @Autowired
    event_service eventService;

    @PostMapping("/add")
    public event_dto addEvent(@RequestBody event_dto eventDto) {
        return eventService.addEvent(eventDto);
    }

    @GetMapping("/all")
    public List<event_dto> getAllEvents() {
        return eventService.getAllEvents();
    }
}
