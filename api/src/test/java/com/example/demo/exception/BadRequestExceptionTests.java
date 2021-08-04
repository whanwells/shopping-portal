package com.example.demo.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class BadRequestExceptionTests {

    @Test
    void constructor() {
        var exception = new BadRequestException("foo");
        assertThat(exception.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(exception.getReason()).isEqualTo("foo");
    }
}
