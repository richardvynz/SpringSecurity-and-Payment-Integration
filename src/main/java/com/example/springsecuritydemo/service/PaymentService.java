package com.example.springsecuritydemo.service;


import com.example.springsecuritydemo.dto.PaymentRequestDto;
import com.example.springsecuritydemo.dto.PaymentResponseDto;
import com.example.springsecuritydemo.dto.VerifyPaymentRequestDto;
import com.example.springsecuritydemo.dto.VerifyPaymentResponseDto;

public interface PaymentService {
    PaymentResponseDto getPayload(PaymentRequestDto paymentRequestDto);
    VerifyPaymentResponseDto verifyTransaction(String reference);

}
