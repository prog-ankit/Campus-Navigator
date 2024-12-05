package com.springboot.university.controller;

import com.springboot.university.requestresponse.request.UserDto;
import com.springboot.university.requestresponse.response.LoginResponseDto;
import com.springboot.university.requestresponse.response.UserResponseDto;
import com.springboot.university.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, UserResponseDto>> register(@RequestBody UserDto userDto) {
        return userService.registerUser(userDto);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, LoginResponseDto>> authenticate(@RequestBody UserDto userDto) {
        return userService.verifyUser(userDto);
    }
}
