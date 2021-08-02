package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductTests {

    private final Product product = new Product();

    @Mock
    private Category category;

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
    void setId() {
        product.setId(1L);
        assertThat(product.getId()).isEqualTo(1);
    }

    @Test
    void setName() {
        product.setName("foo");
        assertThat(product.getName()).isEqualTo("foo");
    }

    @Test
    void setReleaseDate() {
        var date = LocalDate.now();
        product.setReleaseDate(date);
        assertThat(product.getReleaseDate()).isEqualTo(date);
    }

    @Test
    void setMsrp() {
        product.setMsrp(9.99);
        assertThat(product.getMsrp()).isEqualTo(9.99);
    }

    @Test
    void setQuantity() {
        product.setQuantity(5);
        assertThat(product.getQuantity()).isEqualTo(5);
    }

    @Test
    void setCategory() {
        product.setCategory(category);
        assertThat(product.getCategory()).isEqualTo(category);
    }

    @Test
    void getCategoryName() {
        when(category.getName()).thenReturn("foo");

        product.setCategory(category);
        assertThat(product.getCategoryName()).isEqualTo("foo");
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
