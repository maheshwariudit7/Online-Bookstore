package com.OnlineBookstore.OnlineBookStore.config;

import com.OnlineBookstore.OnlineBookStore.entity.User;
import com.OnlineBookstore.OnlineBookStore.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userRepository.findByUsernameOrEmail(username,username).orElseThrow(()->new UsernameNotFoundException(
                "Username " +
                "does not exist"));

        Set<GrantedAuthority> authorities=
                user.getRoles().stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),authorities);

    }
}
