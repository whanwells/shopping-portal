package com.example.demo.user.cart;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class CartItemResponse {

    private final Long id;
    private final CartItemProductResponse product;

    public static CartItemResponse from(CartItem cartItem) {
        return new CartItemResponse(
            cartItem.getId(),
            CartItemProductResponse.from(cartItem.getProduct())
        );
    }

    public static List<CartItemResponse> from(List<CartItem> cartItems) {
        return cartItems.stream().map(CartItemResponse::from).collect(Collectors.toList());
    }
}
