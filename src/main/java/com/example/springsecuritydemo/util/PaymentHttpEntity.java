package com.example.springsecuritydemo.util;

import com.sun.net.httpserver.Headers;
import io.jsonwebtoken.Header;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PaymentHttpEntity <T>{
    public static final String SECRET_KEY = "sk_test_e32d250b1f66f51083d0220f850fdd5f56e992db";


    public HttpEntity<T> getEntity (T body){
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.AUTHORIZATION,"Bearer " + SECRET_KEY);
        header.add(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(body, header);
    }
}
