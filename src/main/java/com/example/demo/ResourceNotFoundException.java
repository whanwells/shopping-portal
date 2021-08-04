package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceNotFoundException extends ResponseStatusException {

    public ResourceNotFoundException(Class<?> c) {
        super(HttpStatus.NOT_FOUND, c.getSimpleName() + " not found");
    }
}
