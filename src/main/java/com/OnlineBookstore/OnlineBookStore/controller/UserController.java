package com.OnlineBookstore.OnlineBookStore.controller;

import com.OnlineBookstore.OnlineBookStore.Dto.UserDto;
import com.OnlineBookstore.OnlineBookStore.entity.User;
import com.OnlineBookstore.OnlineBookStore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bookstore/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/createUser")
    public String createUser(Model model){

        User user=new User();
        model.addAttribute("user",user);
        return "create_user";

    }

    @PostMapping("/saveUser")
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){

        if(result.hasErrors()){
            return "redirect:/bookstore/users/createUser";
        }
        userService.saveUser(user);
        model.addAttribute("user",user);
        return "redirect:/bookstore/users/view";

    }

    @GetMapping("/view")
    public String getUsers(@AuthenticationPrincipal UserDetails userDetails, Model model){
        List<UserDto> users=userService.getAllUsers();
        model.addAttribute("users",users);
        model.addAttribute("userDetails",userDetails);
        return "users";
    }

    @GetMapping("/{userId}/delete")
    public String deleteUser(@PathVariable Long userId ){
        System.out.println(userId+"user id");
        if(userId==null){
            throw new HttpMessageNotReadableException("User id field is empty!");
        }
        userService.deleteById(userId);
        return "redirect:/bookstore/users/view";
    }

    public Optional<UserDto> getUserById(Long userId){
        return userService.getUserByUserid(userId);
    }

    public Optional<UserDto> getUserByUsername(String username){

        return userService.getUserByUsername(username);
    }
}
