package com.example.demo;

public class RegistrationResponse {

    private final long id;
    private final String email;

    public RegistrationResponse(long id, String email) {
        this.id = id;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
