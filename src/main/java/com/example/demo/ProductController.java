package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    List<ProductResponse> getAll(@RequestParam(defaultValue = "") String category) {
        List<Product> products;

        if (!category.isBlank()) {
            products = service.findByCategoryName(category);
        } else {
            products = service.findAll();
        }

        return ProductResponse.from(products);
    }

    @GetMapping("/{id}")
    ProductResponse getById(@PathVariable Long id) {
        var product = service.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(Product.class));

        return ProductResponse.from(product);
    }
}
