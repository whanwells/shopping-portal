package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductTests {

    @Mock
    private Category category;

    @Test
    void constructsWithNoArgs() {
        var product = new Product();

        assertSoftly(softly -> {
            softly.assertThat(product.getId()).isNull();
            softly.assertThat(product.getCategory()).isNull();
            softly.assertThat(product.getName()).isNull();
            softly.assertThat(product.getReleaseDate()).isNull();
            softly.assertThat(product.getMsrp()).isNull();
            softly.assertThat(product.getQuantity()).isNull();
        });
    }

    @Test
    void constructsWithAllProperties() {
        var date = LocalDate.now();
        var product = new Product(1L, category, "bar", date, 9.99, 5);

        assertSoftly(softly -> {
            softly.assertThat(product.getId()).isEqualTo(1);
            softly.assertThat(product.getCategory()).isEqualTo(category);
            softly.assertThat(product.getName()).isEqualTo("bar");
            softly.assertThat(product.getReleaseDate()).isEqualTo(date);
            softly.assertThat(product.getMsrp()).isEqualTo(9.99);
            softly.assertThat(product.getQuantity()).isEqualTo(5);
        });
    }

    @Test
    void constructsWithoutId() {
        var date = LocalDate.now();
        var product = new Product(category, "bar", date, 9.99, 5);

        assertSoftly(softly -> {
            softly.assertThat(product.getId()).isNull();
            softly.assertThat(product.getCategory()).isEqualTo(category);
            softly.assertThat(product.getName()).isEqualTo("bar");
            softly.assertThat(product.getReleaseDate()).isEqualTo(date);
            softly.assertThat(product.getMsrp()).isEqualTo(9.99);
            softly.assertThat(product.getQuantity()).isEqualTo(5);
        });
    }

    @Test
    void setsId() {
        var product = new Product();
        product.setId(1L);
        assertThat(product.getId()).isEqualTo(1);
    }

    @Test
    void setName() {
        var product = new Product();
        product.setName("foo");
        assertThat(product.getName()).isEqualTo("foo");
    }

    @Test
    void setReleaseDate() {
        var product = new Product();
        var date = LocalDate.now();
        product.setReleaseDate(date);
        assertThat(product.getReleaseDate()).isEqualTo(date);
    }

    @Test
    void setMsrp() {
        var product = new Product();
        product.setMsrp(9.99);
        assertThat(product.getMsrp()).isEqualTo(9.99);
    }

    @Test
    void setQuantity() {
        var product = new Product();
        product.setQuantity(5);
        assertThat(product.getQuantity()).isEqualTo(5);
    }

    @Test
    void setsCategory() {
        var product = new Product();
        product.setCategory(category);
        assertThat(product.getCategory()).isEqualTo(category);
    }

    @Test
    void getsCategoryName() {
        when(category.getName()).thenReturn("foo");

        var product = new Product();
        product.setCategory(category);
        assertThat(product.getCategoryName()).isEqualTo("foo");
    }

    @Test
    void isStockedWhenQuantityPositive() {
        var product = new Product();
        product.setQuantity(1);
        assertThat(product.isStocked()).isTrue();
    }

    @Test
    void isNotStockedWhenQuantityNotPositive() {
        var product = new Product();
        product.setQuantity(0);
        assertThat(product.isStocked()).isFalse();
    }
}
