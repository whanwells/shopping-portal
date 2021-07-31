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

    private final Product product = new Product();

    @Test
    void constructs() {
        assertSoftly(softly -> {
            softly.assertThat(product.getId()).isNull();
            softly.assertThat(product.getName()).isNull();
            softly.assertThat(product.getReleaseDate()).isNull();
            softly.assertThat(product.getMsrp()).isNull();
            softly.assertThat(product.getQuantity()).isNull();
            softly.assertThat(product.getCategory()).isNull();
        });
    }

    @Test
    void setsId() {
        product.setId(1L);
        assertThat(product.getId()).isEqualTo(1);
    }

    @Test
    void setName() {
        product.setName("foo");
        assertThat(product.getName()).isEqualTo("foo");
    }

    @Test
    void setsReleaseDate() {
        var date = LocalDate.now();
        product.setReleaseDate(date);
        assertThat(product.getReleaseDate()).isEqualTo(date);
    }

    @Test
    void setsMsrp() {
        product.setMsrp(9.99);
        assertThat(product.getMsrp()).isEqualTo(9.99);
    }

    @Test
    void setsQuantity() {
        product.setQuantity(5);
        assertThat(product.getQuantity()).isEqualTo(5);
    }

    @Test
    void setsCategory() {
        product.setCategory(category);
        assertThat(product.getCategory()).isEqualTo(category);
    }

    @Test
    void getsCategoryName() {
        when(category.getName()).thenReturn("foo");

        product.setCategory(category);
        assertThat(product.getCategoryName()).isEqualTo("foo");
    }

    @Test
    void isStockedWhenQuantityPositive() {
        product.setQuantity(1);
        assertThat(product.isStocked()).isTrue();
    }

    @Test
    void isNotStockedWhenQuantityNotPositive() {
        product.setQuantity(0);
        assertThat(product.isStocked()).isFalse();
    }
}
