package edu.icet.ecom.Service;

import edu.icet.ecom.Model.Dto.user_dto;
import edu.icet.ecom.Model.Entity.user_entity;
import edu.icet.ecom.Repository.user_repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class user_service {
    @Autowired
    user_repository userRepository;
    ModelMapper modelMapper = new ModelMapper();

    public user_dto adduser(user_dto user) {
        user_entity entity = modelMapper.map(user, user_entity.class);
        user_entity savedEntity = userRepository.save(entity);
        return modelMapper.map(savedEntity, user_dto.class);
    }

    public user_dto getUserById(Long id) {
        user_entity entity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return modelMapper.map(entity, user_dto.class);
    }
}

