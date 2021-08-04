package com.example.demo.registration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RegistrationResponse {

    private final Long id;
    private final String email;
}
