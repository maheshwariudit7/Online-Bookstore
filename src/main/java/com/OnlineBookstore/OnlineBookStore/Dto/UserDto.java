package com.OnlineBookstore.OnlineBookStore.Dto;

import com.OnlineBookstore.OnlineBookStore.entity.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private long userid;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private Set<Role> roles;
}
