package com.example.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenManagerConfiguration {

    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public Algorithm algorithm() {
        return Algorithm.HMAC512(secret);
    }

    @Bean
    public JWTCreator.Builder jwtBuilder() {
        return JWT.create();
    }

    @Bean
    public JWTVerifier jwtVerifier() {
        return JWT.require(algorithm()).build();
    }
}
