package edu.icet.ecom.Service;

import edu.icet.ecom.Model.Dto.event_dto;
import edu.icet.ecom.Model.Entity.event_entity;
import edu.icet.ecom.Repository.event_repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class event_service {
    @Autowired
    event_repository eventRepository;
    ModelMapper modelMapper = new ModelMapper();
    public event_dto addEvent(event_dto eventDto) {
        
        event_entity entity = modelMapper.map(eventDto, event_entity.class);
        event_entity saved = eventRepository.save(entity);
        return modelMapper.map(saved, event_dto.class);

    }

    public List<event_dto> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(event -> modelMapper.map(event, event_dto.class))
                .collect(Collectors.toList());
    }
}

