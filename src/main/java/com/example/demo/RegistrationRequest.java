package com.example.demo;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class RegistrationRequest {

    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String password;

    public RegistrationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
