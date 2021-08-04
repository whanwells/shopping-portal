package com.example.demo.user.cart;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class ItemResponse {

    private final ItemProductResponse product;
    private final Integer quantity;

    public static ItemResponse from(Item item) {
        return new ItemResponse(
            ItemProductResponse.from(item.getProduct()),
            item.getQuantity()
        );
    }

    public static List<ItemResponse> from(List<Item> items) {
        return items.stream().map(ItemResponse::from).collect(Collectors.toList());
    }
}
