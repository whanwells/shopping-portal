package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

public class ForbiddenException extends ResponseStatusException {

    public ForbiddenException() {
        super(HttpStatus.FORBIDDEN);
    }

    public static void throwIfPrincipalMismatch(Principal principal, String value) {
        if (!principal.getName().equals(value)) {
            throw new ForbiddenException();
        }
    }

    public static void throwIfPrincipalMismatch(Principal principal, long value) {
        if (!principal.getName().equals(String.valueOf(value))) {
            throw new ForbiddenException();
        }
    }
}
