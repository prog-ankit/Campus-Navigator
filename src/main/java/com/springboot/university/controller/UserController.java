package com.springboot.university.controller;

import com.springboot.university.models.Users;
import com.springboot.university.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class UserController {

    @Autowired
    UsersService usersService;

    @PostMapping("register")
    public Users registerUser(@RequestBody Users user) {
        return usersService.registerUser(user);
    }

    @PostMapping("login")
    public boolean loginUser(@RequestBody Users user) {
        return usersService.verifyUser(user);
    }
}
