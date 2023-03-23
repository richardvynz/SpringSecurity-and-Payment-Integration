package com.example.springsecuritydemo.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DemoSecurityException extends RuntimeException{
    private String message;

    public DemoSecurityException(String userDoesNotExist) {
    }
}
