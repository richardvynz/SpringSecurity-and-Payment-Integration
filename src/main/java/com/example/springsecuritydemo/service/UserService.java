package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.dto.LoginRequest;
import com.example.springsecuritydemo.dto.UserRequest;
import com.example.springsecuritydemo.dto.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserResponse signup(UserRequest userRequest);
    UserDetails loadUserByUsername(String username);
    String login(LoginRequest loginRequest);
}
