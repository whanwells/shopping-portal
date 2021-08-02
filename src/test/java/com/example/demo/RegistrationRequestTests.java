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
    void validate() {
        var request = new RegistrationRequest("foo@example.com", "bar");
        assertThat(validator.validateProperty(request, "password")).isEmpty();
    }

    @Test
    void validateWithNull() {
        var request = new RegistrationRequest(null, null);

        assertSoftly(s -> {
            assertThat(validator.validateProperty(request, "email")).isNotEmpty();
            assertThat(validator.validateProperty(request, "password")).isNotEmpty();
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void validateWithBlankFields(String value) {
        var request = new RegistrationRequest(value, value);

        assertSoftly(s -> {
            assertThat(validator.validateProperty(request, "email")).isNotEmpty();
            assertThat(validator.validateProperty(request, "password")).isNotEmpty();
        });
    }

    @Test
    void validateWithMalformedEmail() {
        var request = new RegistrationRequest("foo", null);
        assertThat(validator.validateProperty(request, "email")).isNotEmpty();
    }
}
