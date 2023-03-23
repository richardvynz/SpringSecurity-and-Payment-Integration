package com.example.springsecuritydemo.dto;

import com.example.springsecuritydemo.entity.PaymentDetail;
import lombok.Data;


@Data
public class PaymentResponseDto {
    private boolean status;
    private String message;
    private PaymentDetail data;
}
