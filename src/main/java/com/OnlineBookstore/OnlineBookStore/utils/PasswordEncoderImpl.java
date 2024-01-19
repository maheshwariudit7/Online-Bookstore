package com.OnlineBookstore.OnlineBookStore.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

public class PasswordEncoderImpl {

    public static void main(String[] args) {

        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.encode("udit"));
        System.out.println(LocalDate.now());
    }
}
