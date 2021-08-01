package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class RegistrationResponseTests {

    @Test
    void constructs() {
        var response = new RegistrationResponse(1L, "foo@example.com");

        assertSoftly(softly -> {
            softly.assertThat(response.getId()).isEqualTo(1L);
            softly.assertThat(response.getEmail()).isEqualTo("foo@example.com");
        });
    }
}
