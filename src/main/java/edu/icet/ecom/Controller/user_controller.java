package edu.icet.ecom.Controller;

import edu.icet.ecom.Model.Dto.user_dto;
import edu.icet.ecom.Model.Entity.user_entity;
import edu.icet.ecom.Service.user_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class user_controller {
    @Autowired
    user_service userService;

    @PostMapping
    public user_dto createUser(@RequestBody user_dto user) {
        return userService.adduser(user);
    }

    @GetMapping("/{id}")
    public user_dto getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

}
