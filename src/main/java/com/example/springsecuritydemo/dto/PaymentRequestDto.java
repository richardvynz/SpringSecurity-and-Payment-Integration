package com.example.springsecuritydemo.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class PaymentRequestDto {
    private String email;
    private BigDecimal amount;
    private String currency;
}
