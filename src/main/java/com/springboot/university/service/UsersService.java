package com.springboot.university.service;

import com.springboot.university.models.Users;
import org.springframework.stereotype.Service;


public interface UsersService {
    Users registerUser(Users user);
    boolean verifyUser(Users user);
}
