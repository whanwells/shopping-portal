package com.example.demo.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductResponseTests {

    @Mock
    private Product product;

    @Mock
    private Category category;

    @Test
    void from() {
        when(product.getId()).thenReturn(1L);
        when(product.getName()).thenReturn("foo");
        when(product.getReleaseDate()).thenReturn(LocalDate.of(2000, 1, 1));
        when(product.getMsrp()).thenReturn(9.99);
        when(product.isStocked()).thenReturn(true);
        when(product.getCategory()).thenReturn(category);
        when(category.getName()).thenReturn("foo");

        var response = ProductResponse.from(product);

        assertThat(response.getId()).isEqualTo(1);
        assertThat(response.getName()).isEqualTo("foo");
        assertThat(response.getCategory()).isEqualTo("foo");
        assertThat(response.getReleaseDate()).isEqualTo(LocalDate.of(2000, 1, 1));
        assertThat(response.getMsrp()).isEqualTo(9.99);
        assertThat(response.isStocked()).isEqualTo(true);
    }
}
