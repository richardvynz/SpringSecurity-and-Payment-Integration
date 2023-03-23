package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.dto.LoginRequest;
import com.example.springsecuritydemo.dto.UserRequest;
import com.example.springsecuritydemo.dto.UserResponse;
import com.example.springsecuritydemo.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;
    @GetMapping("/hello")
    public ResponseEntity<String> sayhello(){
        return  new ResponseEntity<>("hello from this side", HttpStatus.OK);
    }
    @SecurityRequirements()
    @PostMapping("/register")
    public ResponseEntity<UserResponse> signup(@Valid @RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.signup(userRequest);
        return new ResponseEntity<>(userResponse,HttpStatus.CREATED);
    }
    @SecurityRequirements()
    @PostMapping("/login")
    public ResponseEntity<String> login (@Valid @RequestBody LoginRequest loginRequest){
        //LoginResponse loginResponse = userService.login(loginRequest);
        return ResponseEntity.ok(userService.login(loginRequest));
    }

    @GetMapping("/meow")
    public String giveItAnyName(Principal principal){
        return principal.getName();
    }

}
