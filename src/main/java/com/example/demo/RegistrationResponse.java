package com.example.demo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RegistrationResponse {

    private final long id;
    private final String email;
}
