package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class CategoryNotFoundExceptionTests {

    @Test
    void constructs() {
        var e = new CategoryNotFoundException();
        assertSoftly(softly -> {
            softly.assertThat(e.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
            softly.assertThat(e.getReason()).isEqualTo("Category not found");
        });
    }
}
