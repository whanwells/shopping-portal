package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class ProductTests {

    private final Product product = new Product();

    @Test
    void constructor() {
        assertSoftly(s -> {
            s.assertThat(product.getId()).isNull();
            s.assertThat(product.getName()).isNull();
            s.assertThat(product.getReleaseDate()).isNull();
            s.assertThat(product.getMsrp()).isNull();
            s.assertThat(product.getQuantity()).isNull();
            s.assertThat(product.getCategory()).isNull();
        });
    }

    @Test
    void isStockedWithPositiveQuantity() {
        product.setQuantity(1);
        assertThat(product.isStocked()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void isStockedWithNonPositiveQuantity(int quantity) {
        product.setQuantity(quantity);
        assertThat(product.isStocked()).isFalse();
    }
}
