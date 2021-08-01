package com.example.demo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
