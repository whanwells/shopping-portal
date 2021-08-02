package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryTests {

    private final Category category = new Category();

    @Mock
    private Product product;

    @Test
    void addProduct() {
        category.addProduct(product);
        assertThat(category.getProducts()).contains(product);
        verify(product).setCategory(category);
    }

    @Test
    void removeProduct() {
        category.addProduct(product);
        category.removeProduct(product);
        assertThat(category.getProducts()).doesNotContain(product);
        verify(product).setCategory(null);
    }
}
