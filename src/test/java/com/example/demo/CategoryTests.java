package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoryTests {

    @Mock
    private Product product;

    private final Category category = new Category();

    @Test
    void constructs() {
        assertSoftly(softly -> {
            softly.assertThat(category.getId()).isNull();
            softly.assertThat(category.getName()).isNull();
            softly.assertThat(category.getProducts()).isEmpty();
        });
    }

    @Test
    void setsId() {
        var category = new Category();
        category.setId(1L);
        assertThat(category.getId()).isEqualTo(1L);
    }

    @Test
    void setsName() {
        var category = new Category();
        category.setName("foo");
        assertThat(category.getName()).isEqualTo("foo");
    }

    @Test
    void addsProducts() {
        category.addProduct(product);

        assertSoftly(softly -> {
            softly.assertThat(category.getProducts()).contains(product);
            verify(product).setCategory(category);
        });
    }

    @Test
    void removesProducts() {
        category.addProduct(product);
        category.removeProduct(product);

        assertSoftly(softly -> {
            softly.assertThat(category.getProducts()).doesNotContain(product);
            verify(product).setCategory(null);
        });
    }
}
