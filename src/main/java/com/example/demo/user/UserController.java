package com.example.demo.user;

import com.example.demo.ForbiddenException;
import com.example.demo.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAll() {
        return UserResponse.from(service.findAll());
    }

    @GetMapping("/{userId}")
    public UserResponse getOne(@PathVariable Long userId, Principal principal) {
        ForbiddenException.throwIfPrincipalMismatch(principal, userId);

        var user = service.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException(User.class));

        return UserResponse.from(user);
    }
}
