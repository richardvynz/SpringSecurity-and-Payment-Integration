package com.example.springsecuritydemo.entity;

import lombok.Data;

@Data
public class VerifyPaymentDetail {
    private Integer id;
    private String domain;
    private String status;
    private String reference;
    private Integer amount;
    private Object message;
    private String gatewayResponse;
    private String paidAt;
    private String createdAt;
    private String channel;
    private String currency;
    private String ipAddress;
}
