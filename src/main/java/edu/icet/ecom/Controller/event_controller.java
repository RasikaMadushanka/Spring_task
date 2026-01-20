package edu.icet.ecom.Controller;

import edu.icet.ecom.Model.Dto.event_dto;
import edu.icet.ecom.Service.event_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class event_controller {
    @Autowired
    event_service eventService;

    @PostMapping
    public event_dto addEvent(@RequestBody event_dto eventDto) {
        return eventService.addEvent(eventDto);
    }

    @GetMapping
    public List<event_dto> getAllEvents() {
        return eventService.getAllEvents();
    }
}
