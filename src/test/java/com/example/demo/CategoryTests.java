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

    @Test
    void constructsWithNoArgs() {
        var category = new Category();

        assertSoftly(softly -> {
            softly.assertThat(category.getId()).isNull();
            softly.assertThat(category.getName()).isNull();
            softly.assertThat(category.getProducts()).hasSize(0);
        });
    }

    @Test
    void constructsWithAllArgs() {
        var category = new Category(1L, "foo");

        assertSoftly(softly -> {
            softly.assertThat(category.getId()).isEqualTo(1);
            softly.assertThat(category.getName()).isEqualTo("foo");
            softly.assertThat(category.getProducts()).hasSize(0);
        });
    }

    @Test
    void constructsWithoutId() {
        var category = new Category("foo");

        assertSoftly(softly -> {
            softly.assertThat(category.getId()).isNull();
            softly.assertThat(category.getName()).isEqualTo("foo");
            softly.assertThat(category.getProducts()).hasSize(0);
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
    void setsProducts() {
        var products = Set.of(product);
        var category = new Category();
        category.setProducts(products);
        assertThat(category.getProducts()).isEqualTo(products);
    }

    @Test
    void setsProductCategories() {
        var products = Set.of(product);
        var category = new Category();
        category.setProducts(products);
        verify(product).setCategory(category);
    }
}
