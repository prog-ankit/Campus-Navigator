package com.springboot.university.service.serviceimpl;

import com.springboot.university.models.Users;
import com.springboot.university.repository.UsersRepository;
import com.springboot.university.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    JWTServiceImpl jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Override
    public Users registerUser(Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return usersRepository.save(user);
    }

    //Earlier authentication was managed by default but since we have defined an Authentication Manager, we need to verify if the user is present in database or not.
    @Override
    public boolean verifyUser(Users user) {
        //UsernamePasswordAuthenticationToken is a class that implements Authentication interface.
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated()) {
            System.out.println(jwtService.generateToken(user.getUsername()));
            return true;
        }
        System.out.println("Failure");
        return false;
    }
}
