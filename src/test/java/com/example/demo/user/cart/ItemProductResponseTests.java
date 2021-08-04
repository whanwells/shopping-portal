package com.example.demo.user.cart;

import com.example.demo.product.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemProductResponseTests {

    @Mock
    private Product product;

    @Test
    void from() {
        when(product.getId()).thenReturn(1L);
        when(product.getName()).thenReturn("foo");
        when(product.getMsrp()).thenReturn(9.99);
        var response = ItemProductResponse.from(product);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("foo");
        assertThat(response.getMsrp()).isEqualTo(9.99);
    }
}
