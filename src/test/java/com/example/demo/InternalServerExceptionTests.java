package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class InternalServerExceptionTests {

    @Test
    void constructs() {
        var exception = new InternalServerException("foo");

        assertSoftly(softly -> {
            softly.assertThat(exception.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
            softly.assertThat(exception.getReason()).isEqualTo("foo");
        });
    }
}
