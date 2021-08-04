package com.example.demo.product;

import com.example.demo.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping
    List<CategoryResponse> getAll(@RequestParam(defaultValue = "") String category) {
        List<Category> categories;

        if (!category.isBlank()) {
            categories = categoryService.findByName(category);
        } else {
            categories = categoryService.findAll();
        }

        return CategoryResponse.from(categories);
    }

    @GetMapping("/{productId}")
    ProductResponse getOne(@PathVariable Long productId) {
        var product = productService.findById(productId)
            .orElseThrow(() -> new ResourceNotFoundException(Product.class));

        return ProductResponse.from(product);
    }
}
