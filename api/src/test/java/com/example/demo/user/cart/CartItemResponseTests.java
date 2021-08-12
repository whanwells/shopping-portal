package com.example.demo.user.cart;

import com.example.demo.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartItemResponseTests {

    @Mock
    private CartItem cartItem;

    @Mock
    private Product product;

    @BeforeEach
    void setup() {
        when(cartItem.getProduct()).thenReturn(product);
    }

    @Test
    void fromItem() {
        var response = CartItemResponse.from(cartItem);
        assertThat(response.getProduct()).isInstanceOf(CartItemProductResponse.class);
    }

    @Test
    void fromItemList() {
        var response = CartItemResponse.from(List.of(cartItem));
        assertThat(response).isNotEmpty();
    }
}
