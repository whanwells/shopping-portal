package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class RegistrationRequestTests {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void constructs() {
        var request = new RegistrationRequest("foo@example.com", "bar");

        assertSoftly(softly -> {
            softly.assertThat(request.getEmail()).isEqualTo("foo@example.com");
            softly.assertThat(request.getPassword()).isEqualTo("bar");
        });
    }

    @Test
    void failsValidationWhenEmailNull() {
        var request = new RegistrationRequest(null, null);
        assertThat(validator.validateProperty(request, "email")).isNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "foo"})
    void failsValidationWhenEmailMalformed(String value) {
        var request = new RegistrationRequest(value, null);
        assertThat(validator.validateProperty(request, "email")).isNotEmpty();
    }

    @Test
    void failsValidationWhenPasswordNull() {
        var request = new RegistrationRequest(null, null);
        assertThat(validator.validateProperty(request, "password")).isNotEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void failsValidationWhenPasswordBlank(String value) {
        var request = new RegistrationRequest(null, value);
        assertThat(validator.validateProperty(request, "password")).isNotEmpty();
    }

    @Test
    void passesValidationWhenAllArgsValid() {
        var request = new RegistrationRequest("foo@example.com", "bar");
        assertThat(validator.validateProperty(request, "password")).isEmpty();
    }
}
