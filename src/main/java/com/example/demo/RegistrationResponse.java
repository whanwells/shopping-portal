package com.example.demo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RegistrationResponse {

    private final long id;
    private final String email;

    public static RegistrationResponse from(User user) {
        return new RegistrationResponse(user.getId(), user.getEmail());
    }
}
