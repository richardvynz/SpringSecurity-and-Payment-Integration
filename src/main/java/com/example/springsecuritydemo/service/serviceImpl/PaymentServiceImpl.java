package com.example.springsecuritydemo.service.serviceImpl;

import com.example.springsecuritydemo.dto.PaymentRequestDto;
import com.example.springsecuritydemo.dto.PaymentResponseDto;
import com.example.springsecuritydemo.dto.VerifyPaymentRequestDto;
import com.example.springsecuritydemo.dto.VerifyPaymentResponseDto;
import com.example.springsecuritydemo.service.PaymentService;
import com.example.springsecuritydemo.util.PaymentHttpEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final RestTemplate restTemplate;
    private final PaymentHttpEntity<Object> paymentHttpEntity;

    @Override
    public PaymentResponseDto getPayload(PaymentRequestDto paymentRequestDto) {
        String url = "https://api.paystack.co/transaction/initialize";
//        ResponseEntity<PaymentResponseDto> paymentResponseDtoResponseEntity =
//                restTemplate.postForEntity(url, paymentHttpEntity.getEntity(paymentRequestDto), PaymentResponseDto.class);
//        return paymentResponseDtoResponseEntity.getBody();

//        PaymentResponseDto paymentResponseDto =
//                restTemplate.postForObject(url, paymentHttpEntity.getEntity(paymentRequestDto), PaymentResponseDto.class);

//        return paymentResponseDto;

        ResponseEntity<PaymentResponseDto> exchange = restTemplate.exchange(url, HttpMethod.POST,
                paymentHttpEntity.getEntity(paymentRequestDto), PaymentResponseDto.class);
        return exchange.getBody();
    }

    @Override
    public VerifyPaymentResponseDto verifyTransaction(String  reference) {
        String url = "https://api.paystack.co/transaction/verify/" + reference;

        ResponseEntity<VerifyPaymentResponseDto> exchange = restTemplate.exchange(url, HttpMethod.GET,paymentHttpEntity.getEntity(null),
                VerifyPaymentResponseDto.class);

        return exchange.getBody();
    }



}
