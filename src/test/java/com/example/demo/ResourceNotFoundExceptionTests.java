package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class ResourceNotFoundExceptionTests {

    @Test
    void constructor() {
        var exception = new ResourceNotFoundException(ResourceNotFoundException.class);

        assertSoftly(s -> {
            s.assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
            s.assertThat(exception.getReason()).isEqualTo("ResourceNotFoundException not found");
        });
    }
}
