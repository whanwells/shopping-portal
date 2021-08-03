package com.example.demo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class OrderLineResponse {

    private final long id;
    private final ProductResponse product;
    private final int quantity;

    public static OrderLineResponse from(OrderLine orderLine) {
        return new OrderLineResponse(
            orderLine.getId(),
            ProductResponse.from(orderLine.getProduct()),
            orderLine.getQuantity()
        );
    }

    public static List<OrderLineResponse> from(List<OrderLine> orderLines) {
        return orderLines.stream().map(OrderLineResponse::from).collect(Collectors.toList());
    }
}
