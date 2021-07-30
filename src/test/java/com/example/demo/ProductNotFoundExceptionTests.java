package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class ProductNotFoundExceptionTests {

    @Test
    void constructs() {
        var e = new ProductNotFoundException();
        assertSoftly(softly -> {
            softly.assertThat(e.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
            softly.assertThat(e.getReason()).isEqualTo("Product not found");
        });
    }
}
