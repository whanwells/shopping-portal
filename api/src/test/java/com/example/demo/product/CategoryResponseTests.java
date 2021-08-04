package com.example.demo.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryResponseTests {

    @Mock
    private Category category;

    @Mock
    private Product product;

    @Test
    void from() {
        when(category.getName()).thenReturn("foo");
        when(category.getProducts()).thenReturn(List.of(product));

        var response = CategoryResponse.from(category);

        assertThat(response.getCategory()).isEqualTo("foo");
        assertThat(response.getProducts()).isNotEmpty();
    }
}
