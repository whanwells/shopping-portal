package com.example.demo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class RoleResponse {

    private final long id;
    private final String name;

    public static RoleResponse from(Role role) {
        return new RoleResponse(role.getId(), role.getName());
    }

    public static List<RoleResponse> from (List<Role> roles) {
        return roles.stream().map(RoleResponse::from).collect(Collectors.toList());
    }
}
