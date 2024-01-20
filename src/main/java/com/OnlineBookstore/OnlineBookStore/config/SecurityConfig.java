package com.OnlineBookstore.OnlineBookStore.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
@EnableMethodSecurity

@EnableWebSecurity
public class SecurityConfig {

    // from spring security 6 onwards we do not have to provide userdetailsservice bean to AuthenticationManager
    // manually
    // it is done automatically by spring security..we just have to create an instance variable here
    public UserDetailsService userDetailsService;

    // use below for normal database authentication

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
//
//        httpSecurity.csrf(csrf->csrf.disable()).authorizeHttpRequests(authorize->{
//
//            authorize.requestMatchers(HttpMethod.GET,"/bookstore/orders/**").hasRole("ADMIN")
//            .requestMatchers(HttpMethod.GET,"/login").permitAll();
////            authorize.anyRequest().authenticated();
//        }).httpBasic(Customizer.withDefaults());
//
//        return httpSecurity.build();
//    }

    // use below for custom login form based authentication

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(csrf->csrf.disable()).authorizeHttpRequests(authorize->
                authorize
//                        .anyRequest().authenticated()
                        .requestMatchers("/login","/signup","/users","/home").permitAll()
                        .requestMatchers("/bookstore/books**","/bookstore/books/**","/bookstore/users**",
                                "/bookstore/users/**","/bookstore/users/view","/bookstore/orders/view",
                                "/bookstore/orders/{orderId}/view","/bookstore/orders/{orderId}/delete",
                                "/bookstore/books/{bookId}/addToCart**","/bookstore/books/{bookId}/removeFromCart**",
                                "/bookstore/books/cart/createCart","bookstore/orders/postOrder","bookstore/orders" +
                                        "/postOrder/**","/bookstore/orders/user/{userId}/view","/bookstore/orders" +
                                        "/{userId}/{orderId}/view","/bookstore/books/{bookId}/edit","/bookstore/books" +
                                        "/{bookId}/saveBook"
                                ).permitAll()
                        .requestMatchers("bookstore/users/{userID}/delete").permitAll()
//                        .requestMatchers(HttpMethod.GET,"/bookstore/orders/**").hasRole("USER")
//                        .requestMatchers(HttpMethod.DELETE,"/bookstore/**").hasRole("ADMIN")


                )
                .formLogin(form->
                form.loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/home")
                        .permitAll());

        return httpSecurity.build();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
