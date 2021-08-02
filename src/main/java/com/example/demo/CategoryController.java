package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    List<Category> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    Category getById(@PathVariable Long id) {
        return service.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(Category.class));
    }
}
