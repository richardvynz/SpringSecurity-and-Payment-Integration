package com.example.springsecuritydemo.util;


import com.example.springsecuritydemo.dto.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResponseProvider {
    public ResponseEntity<ApiResponse<Object>> success(Object payload) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(payload));
    }
    public ResponseEntity<ApiResponse<Object>>success(String message, Object payload) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(payload));
    }
}