package com.example.springsecuritydemo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "field required")
    private String email;
    @NotBlank(message = "field required")
    private String password;
}
