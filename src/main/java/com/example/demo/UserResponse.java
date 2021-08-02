package com.example.demo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class UserResponse {

    private final long id;
    private final String email;
    private final List<RoleResponse> roles;

    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getEmail(), RoleResponse.from(user.getRoles()));
    }

    public static List<UserResponse> from(List<User> users) {
        return users.stream().map(UserResponse::from).collect(Collectors.toList());
    }
}
