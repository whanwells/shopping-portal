package com.example.demo.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTests {

    private final Product product = new Product();

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

    @Test
    void reduceQuantityBy() {
        product.setQuantity(5);
        product.reduceQuantityBy(4);
        assertThat(product.getQuantity()).isEqualTo(1);
    }
}
