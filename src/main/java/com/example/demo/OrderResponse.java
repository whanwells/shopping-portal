package com.example.demo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class OrderResponse {
    private final long id;
    private final UserResponse user;
    private final LocalDateTime date;
    private final List<OrderLineResponse> lines;

    public static OrderResponse from(Order order) {
        return new OrderResponse(
            order.getId(),
            UserResponse.from(order.getUser()),
            order.getDate(),
            OrderLineResponse.from(order.getLines())
        );
    }

    public static List<OrderResponse> from(List<Order> orders) {
        return orders.stream().map(OrderResponse::from).collect(Collectors.toList());
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public static class UserResponse {
        private final long id;
        private final String email;

        private static UserResponse from(User user) {
            return new UserResponse(user.getId(), user.getEmail());
        }
    }
}
