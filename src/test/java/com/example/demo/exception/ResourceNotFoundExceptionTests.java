package com.example.demo.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class ResourceNotFoundExceptionTests {

    @Test
    void constructor() {
        var exception = new ResourceNotFoundException(ResourceNotFoundException.class);
        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(exception.getReason()).isEqualTo("ResourceNotFoundException not found");
    }
}
