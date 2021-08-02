package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public List<UserResponse> getAll() {
        return UserResponse.from(service.findAll());
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id) {
        var user = service.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(User.class));

        return UserResponse.from(user);
    }
}
