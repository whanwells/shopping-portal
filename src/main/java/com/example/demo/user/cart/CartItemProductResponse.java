package com.example.demo.user.cart;

import com.example.demo.product.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
class CartItemProductResponse {

    private final Long id;
    private final String name;
    private final Double msrp;

    public static CartItemProductResponse from(Product product) {
        return new CartItemProductResponse(product.getId(), product.getName(), product.getMsrp());
    }
}
