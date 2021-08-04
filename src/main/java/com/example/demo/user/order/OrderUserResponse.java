package com.example.demo.user.order;

import com.example.demo.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
class OrderUserResponse {
    private final long id;
    private final String email;

    public static OrderUserResponse from(User user) {
        return new OrderUserResponse(user.getId(), user.getEmail());
    }
}
