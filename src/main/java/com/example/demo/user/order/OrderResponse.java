package com.example.demo.user.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class OrderResponse {
    private final long id;
    private final OrderUserResponse user;
    private final LocalDateTime date;
    private final List<OrderLineResponse> products;

    public static OrderResponse from(Order order) {
        return new OrderResponse(
            order.getId(),
            OrderUserResponse.from(order.getUser()),
            order.getDate(),
            OrderLineResponse.from(order.getOrderLines())
        );
    }

    public static List<OrderResponse> from(List<Order> orders) {
        return orders.stream().map(OrderResponse::from).collect(Collectors.toList());
    }
}
