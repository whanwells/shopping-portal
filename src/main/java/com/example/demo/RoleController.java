package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Role> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Role getById(@PathVariable Long id) {
        return service.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(Role.class));
    }
}
