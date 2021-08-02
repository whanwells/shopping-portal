package com.example.demo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Getter
public class RegistrationRequest {

    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String password;
}
