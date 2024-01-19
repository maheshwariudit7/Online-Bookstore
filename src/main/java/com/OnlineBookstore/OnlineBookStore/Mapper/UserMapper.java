package com.OnlineBookstore.OnlineBookStore.Mapper;

import com.OnlineBookstore.OnlineBookStore.Dto.UserDto;
import com.OnlineBookstore.OnlineBookStore.entity.User;

public class UserMapper {

    public static User mapToUser(UserDto userDto){

        User user=new User();
        user.setEmail(userDto.getEmail());
        user.setUserid(userDto.getUserid());
        user.setRoles(userDto.getRoles());
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        return user;
    }

    public static UserDto mapToUserDto(User user){

        UserDto userDto=new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setUserid(user.getUserid());
        userDto.setRoles(user.getRoles());
        userDto.setPassword(user.getPassword());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        return userDto;
    }
}
