package com.example.springsecuritydemo.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private Collection message;
}