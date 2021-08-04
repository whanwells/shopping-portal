package com.example.demo.cart;

import com.example.demo.product.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ItemProductResponse {

    private final Long id;
    private final String name;
    private final Double msrp;

    public static ItemProductResponse from(Product product) {
        return new ItemProductResponse(product.getId(), product.getName(), product.getMsrp());
    }
}
