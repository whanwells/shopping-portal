package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    List<Product> getAll(@RequestParam(defaultValue = "") String category) {
        if (!category.isBlank()) {
            return service.findByCategoryName(category);
        }
        return service.findAll();
    }

    @GetMapping("/{id}")
    Product getById(@PathVariable Long id) {
        return service.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(Product.class));
    }
}
