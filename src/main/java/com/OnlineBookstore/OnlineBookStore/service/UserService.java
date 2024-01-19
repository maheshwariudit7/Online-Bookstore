package com.OnlineBookstore.OnlineBookStore.service;

import com.OnlineBookstore.OnlineBookStore.Dto.UserDto;
import com.OnlineBookstore.OnlineBookStore.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {


    UserDto saveUser(User user);

    void deleteById(Long userId);

    List<UserDto> getAllUsers();

    Optional<UserDto> getUserByUsername(String username);

    Optional<UserDto> getUserByUserid(Long userId);

}
