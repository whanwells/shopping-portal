package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class BadRequestExceptionTests {

    @Test
    void constructor() {
        var exception = new BadRequestException("foo");

        assertSoftly(s -> {
            s.assertThat(exception.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
            s.assertThat(exception.getReason()).isEqualTo("foo");
        });
    }
}
