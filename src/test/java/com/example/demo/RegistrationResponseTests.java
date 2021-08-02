package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class RegistrationResponseTests {

    @Test
    void constructor() {
        var response = new RegistrationResponse(1L, "foo@example.com");

        assertSoftly(s -> {
            s.assertThat(response.getId()).isEqualTo(1L);
            s.assertThat(response.getEmail()).isEqualTo("foo@example.com");
        });
    }
}
