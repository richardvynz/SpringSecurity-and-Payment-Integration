package com.example.springsecuritydemo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    @NotBlank(message = "please enter your firstName!")
    private String firstName;
    @NotBlank(message = "please enter your firstName!")
    private String lastName;
    @Email
    @NotBlank(message = "email is required!")
    private String email;
    @NotBlank(message = "password is required!")
    private String password;
}
