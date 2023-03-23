package com.example.springsecuritydemo.controller;
import com.example.springsecuritydemo.dto.ApiResponse;
import com.example.springsecuritydemo.dto.PaymentRequestDto;
import com.example.springsecuritydemo.dto.VerifyPaymentResponseDto;
import com.example.springsecuritydemo.service.PaymentService;
import com.example.springsecuritydemo.util.ResponseProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class PaymentController {
   private final ResponseProvider responseProvider;
   private final PaymentService paymentService;
    @PostMapping("/initializePayment")
    public ResponseEntity<ApiResponse<Object>> aMethodName(Principal principal, @RequestBody PaymentRequestDto paymentRequestDto) {
        return responseProvider.success(paymentService.getPayload(paymentRequestDto));
    }

    @GetMapping("/VerifyPayment/{reference}")
    public ResponseEntity<ApiResponse<Object>>verifyPayment(@PathVariable String reference){
        return responseProvider.success(paymentService.verifyTransaction(reference));
    }
}
