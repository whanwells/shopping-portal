package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService service;

    @GetMapping
    public List<RoleResponse> getAll() {
        return RoleResponse.from(service.findAll());
    }

    @GetMapping("/{id}")
    public RoleResponse getById(@PathVariable Long id) {
        var role = service.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(Role.class));

        return RoleResponse.from(role);
    }
}
