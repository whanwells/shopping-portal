package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class ResourceNotFoundExceptionTests {

    @Test
    void constructs() {
        var exception = new ResourceNotFoundException(ResourceNotFoundException.class);

        assertSoftly(softly -> {
            softly.assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
            softly.assertThat(exception.getReason()).isEqualTo("ResourceNotFoundException not found");
        });
    }
}
