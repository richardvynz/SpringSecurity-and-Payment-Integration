package com.example.springsecuritydemo.entity;

import lombok.Data;

@Data
public class PaymentDetail {
    private String authorization_url;
    private String access_code;
    private String reference;
}
