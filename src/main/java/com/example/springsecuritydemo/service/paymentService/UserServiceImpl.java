package com.example.springsecuritydemo.service.paymentService;

import com.example.springsecuritydemo.config.JwtUtils;
import com.example.springsecuritydemo.config.UserPrincipal;
import com.example.springsecuritydemo.dto.LoginRequest;
import com.example.springsecuritydemo.dto.UserRequest;
import com.example.springsecuritydemo.dto.UserResponse;
import com.example.springsecuritydemo.entity.User;
import com.example.springsecuritydemo.exception.DemoSecurityException;
import com.example.springsecuritydemo.repository.UserRepository;
import com.example.springsecuritydemo.service.UserService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;


    @Override
    public UserResponse signup(UserRequest userRequest) {
        User user  = User.builder()
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .build();
        User saveUser = userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user,userResponse);
        return userResponse;
    }
    private User getUser(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new DemoSecurityException("USER_DOES_NOT_EXIST"));
    }

    @Override
    public String login(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtils.generateToken(loginRequest.getEmail());

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return new UserPrincipal(getUser(username));
    }

}
