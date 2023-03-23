package com.example.springsecuritydemo.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private ErrorResponse buildErrorResponse(Object error, HttpStatus status){
        return ErrorResponse.builder()
                .status(status.value())
                .message(Collections.singletonList(error))
                .build();
    }
    private static List<String> getValidationMessage(BindingResult bindingResult) {
        return bindingResult.getAllErrors()
                .stream()
                .map(GlobalExceptionHandler::getValidationMessage)
                .collect(Collectors.toList());
    }
    private static String getValidationMessage(ObjectError error) {
        if (error instanceof FieldError fieldError) {
            String className = fieldError.getObjectName();
            String property = fieldError.getField();
            Object invalidValue = fieldError.getRejectedValue();
            String message = fieldError.getDefaultMessage();
            return String.format("%s.%s %s, but it was %s", className, property, message, invalidValue);
        }
        return String.format("%s: %s", error.getObjectName(), error.getDefaultMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException e) {
        List<String> response = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            response.add(String.format("%s : %s", fieldName, errorMessage));
        });
        log.error(e.getMessage());
        return buildErrorResponse(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {DemoSecurityException.class})
    public ErrorResponse handleServerException(DemoSecurityException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequest(BadCredentialsException ex) {
        log.error(ex.getMessage());
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = JwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleJwtException(JwtException ex) {
        log.error(ex.getMessage());
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleExpiredJwtException(ExpiredJwtException ex) {
        log.error(ex.getMessage());
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception ex) {
        log.error("ERROR OCCURRED! " + ex.getMessage());
        return buildErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
