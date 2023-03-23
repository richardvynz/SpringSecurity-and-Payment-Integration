package com.example.springsecuritydemo.dto;

import com.example.springsecuritydemo.entity.VerifyPaymentDetail;
import lombok.Data;
@Data
public class VerifyPaymentResponseDto {
private Boolean status;
private String message;
//private VerifyPaymentDetail data;
}