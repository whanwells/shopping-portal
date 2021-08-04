package com.example.demo.user.cart;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class CartItemResponse {

    private final CartItemProductResponse product;
    private final Integer quantity;

    public static CartItemResponse from(CartItem cartItem) {
        return new CartItemResponse(
            CartItemProductResponse.from(cartItem.getProduct()),
            cartItem.getQuantity()
        );
    }

    public static List<CartItemResponse> from(List<CartItem> cartItems) {
        return cartItems.stream().map(CartItemResponse::from).collect(Collectors.toList());
    }
}
