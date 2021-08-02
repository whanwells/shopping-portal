package com.example.demo;

import lombok.Getter;

@Getter
public class RegistrationResponse {

    private final long id;
    private final String email;

    public RegistrationResponse(long id, String email) {
        this.id = id;
        this.email = email;
    }
}
