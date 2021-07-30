package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    List<Category> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    Category getById(@PathVariable Long id) throws CategoryNotFoundException {
        return service.findById(id)
            .orElseThrow(CategoryNotFoundException::new);
    }
}
