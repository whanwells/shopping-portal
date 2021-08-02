package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductResponseTests {

    @Mock
    private Product product;

    @Mock
    private Category category;

    @BeforeEach
    void setupProduct() {
        when(product.getId()).thenReturn(1L);
        when(product.getName()).thenReturn("foo");
        when(product.getReleaseDate()).thenReturn(LocalDate.of(2000, 1, 1));
        when(product.getMsrp()).thenReturn(9.99);
        when(product.getCategory()).thenReturn(category);
        when(product.isStocked()).thenReturn(true);
    }

    @BeforeEach
    void setupCategory() {
        when(category.getName()).thenReturn("bar");
    }

    @Test
    void fromWithProduct() {
        var response = ProductResponse.from(product);

        assertSoftly(s -> {
            s.assertThat(response.getId()).isEqualTo(1);
            s.assertThat(response.getName()).isEqualTo("foo");
            s.assertThat(response.getReleaseDate()).isEqualTo(LocalDate.of(2000, 1, 1));
            s.assertThat(response.getMsrp()).isEqualTo(9.99);
            s.assertThat(response.getCategory()).isEqualTo("bar");
            s.assertThat(response.isStocked()).isEqualTo(true);
        });
    }

    @Test
    void fromWithProductList() {
        var response = ProductResponse.from(List.of(product));

        assertSoftly(s -> {
            s.assertThat(response).hasSize(1);
            s.assertThat(response.get(0).getId()).isEqualTo(1);
            s.assertThat(response.get(0).getName()).isEqualTo("foo");
            s.assertThat(response.get(0).getReleaseDate()).isEqualTo(LocalDate.of(2000, 1, 1));
            s.assertThat(response.get(0).getMsrp()).isEqualTo(9.99);
            s.assertThat(response.get(0).getCategory()).isEqualTo("bar");
            s.assertThat(response.get(0).isStocked()).isEqualTo(true);
        });
    }
}
