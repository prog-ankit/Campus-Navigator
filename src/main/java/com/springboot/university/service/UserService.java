package com.springboot.university.service;

import com.springboot.university.models.User;
import com.springboot.university.requestresponse.request.UserDto;
import com.springboot.university.requestresponse.response.LoginResponseDto;
import com.springboot.university.requestresponse.response.UserResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    ResponseEntity<Map<String,UserResponseDto>> registerUser(UserDto user);
    ResponseEntity<Map<String,LoginResponseDto>> verifyUser(UserDto user);
}
