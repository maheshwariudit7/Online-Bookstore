package com.OnlineBookstore.OnlineBookStore.service.impl;

import com.OnlineBookstore.OnlineBookStore.Dto.UserDto;
import com.OnlineBookstore.OnlineBookStore.Mapper.UserMapper;
import com.OnlineBookstore.OnlineBookStore.entity.User;
import com.OnlineBookstore.OnlineBookStore.repository.UserRepository;
import com.OnlineBookstore.OnlineBookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDto saveUser(User user) {

        userRepository.save(user);

        UserDto userDto= UserMapper.mapToUserDto(user);
        return userDto;
    }


    @Override
    public void deleteById(Long userId) {
        if(userRepository.findById(userId).isEmpty()){
            throw new HttpMessageNotReadableException("User not found");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=userRepository.findAll();
        return users.stream().map(user->UserMapper.mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getUserByUsername(String username) {
        Optional<User> user=userRepository.findByUsernameOrEmail(username,username);
        if(!user.isEmpty()){
            UserDto userDto=UserMapper.mapToUserDto(user.get());
            return Optional.of(userDto);
        }

        throw new HttpMessageNotReadableException("User not found");
    }

    @Override
    public Optional<UserDto> getUserByUserid(Long userId) {

        Optional<User> user=userRepository.findByUserid(userId);
        if(user.isPresent()){
            return Optional.of(UserMapper.mapToUserDto(user.get()));
        }

        throw new HttpMessageNotReadableException("User not found");
    }


}
