package com.example.demo.cart;

import com.example.demo.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemResponseTests {

    @Mock
    private Item item;

    @Mock
    private Product product;

    @BeforeEach
    void setup() {
        when(item.getProduct()).thenReturn(product);
        when(item.getQuantity()).thenReturn(9);
    }

    @Test
    void fromItem() {
        var response = ItemResponse.from(item);
        assertThat(response.getProduct()).isInstanceOf(ItemProductResponse.class);
        assertThat(response.getQuantity()).isEqualTo(9);
    }

    @Test
    void fromItemList() {
        var response = ItemResponse.from(List.of(item));
        assertThat(response).isNotEmpty();
    }
}
