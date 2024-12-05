package com.springboot.university.mapper;

import com.springboot.university.models.User;
import com.springboot.university.requestresponse.request.UserDto;
import com.springboot.university.requestresponse.response.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);
    User userDtoToUser(UserDto userDto);
    UserDto userToUserDto(User user);
    UserResponseDto userToUserResponseDto(User user);

}
