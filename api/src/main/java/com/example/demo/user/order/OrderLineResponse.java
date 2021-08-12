package com.example.demo.user.order;

import com.example.demo.product.ProductResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class OrderLineResponse {

    private final long id;
    private final ProductResponse product;

    public static OrderLineResponse from(OrderLine orderLine) {
        return new OrderLineResponse(
            orderLine.getId(),
            ProductResponse.from(orderLine.getProduct())
        );
    }

    public static List<OrderLineResponse> from(List<OrderLine> orderLines) {
        return orderLines.stream().map(OrderLineResponse::from).collect(Collectors.toList());
    }
}
