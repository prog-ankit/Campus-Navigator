package com.springboot.university.service.serviceimpl;

import com.springboot.university.mapper.UserMapper;
import com.springboot.university.models.User;
import com.springboot.university.repository.UserRepository;
import com.springboot.university.requestresponse.request.UserDto;
import com.springboot.university.requestresponse.response.LoginResponseDto;
import com.springboot.university.requestresponse.response.UserResponseDto;
import com.springboot.university.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTServiceImpl jwtService;


    @Override
    public ResponseEntity<Map<String,UserResponseDto>> registerUser(UserDto userDto) {
        Map<String,UserResponseDto> responseMap = new HashMap<>();
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userDto.setRoles("USER");

        User savedUser = userRepo.save(UserMapper.USER_MAPPER.userDtoToUser(userDto));
        responseMap.put("Success", UserMapper.USER_MAPPER.userToUserResponseDto(savedUser));
        return ResponseEntity.ok(responseMap);
    }

    @Override
    public ResponseEntity<Map<String,LoginResponseDto>> verifyUser(UserDto userDto) {
        //As we have defined custom Authentication Manager we will use that to check if username and password are valid or not.
        if(userRepo.findByUsername(userDto.getUsername())!= null) {
            System.out.println("YES GOT THE USER");
        } else {
            System.out.println("FAILED");
        }
        Map<String,LoginResponseDto> responseMap = new HashMap<>();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(),userDto.getPassword()));
        System.out.println("Coming HERE");
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        if (authentication.isAuthenticated()) {
            loginResponseDto.setExpiry(String.valueOf(new Date(System.currentTimeMillis() + 60 * 60 * 60)));
            loginResponseDto.setToken(jwtService.generateToken(userDto.getUsername()));
            responseMap.put("Success",loginResponseDto);
            return ResponseEntity.ok(responseMap);
        }
        responseMap.put("Failure",null);
        return ResponseEntity.status(401).body(responseMap);

    }
}
