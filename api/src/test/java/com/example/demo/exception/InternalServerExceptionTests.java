package com.example.demo.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class InternalServerExceptionTests {

    @Test
    void constructor() {
        var exception = new InternalServerException("foo");
        assertThat(exception.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(exception.getReason()).isEqualTo("foo");
    }
}
