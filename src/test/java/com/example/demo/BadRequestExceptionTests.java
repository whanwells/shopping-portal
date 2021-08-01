package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class BadRequestExceptionTests {

    @Test
    void constructs() {
        var exception = new BadRequestException("foo");

        assertSoftly(softly -> {
            softly.assertThat(exception.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
            softly.assertThat(exception.getReason()).isEqualTo("foo");
        });
    }
}
