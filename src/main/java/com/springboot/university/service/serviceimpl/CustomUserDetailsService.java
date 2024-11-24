package com.springboot.university.service.serviceimpl;

import com.springboot.university.requestresponse.request.CustomUserDetails;
import com.springboot.university.models.Users;
import com.springboot.university.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//UserDetailsService is a built in interface which implements the authentication of user
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Finding User...");
        Users user = usersRepository.findByUsername(username);
        if(user == null) {
            System.out.println("user Not Found");
            throw new UsernameNotFoundException("User not Found");
        }
        System.out.println("User Found");
        System.out.println(user.getUsername());

        return new CustomUserDetails(user);
    }
}
