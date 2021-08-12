package com.example.demo.product;

import com.example.demo.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    List<ProductResponse> getAll(@RequestParam(defaultValue = "") String category) {
        List<Product> products;

        if (!category.isBlank()) {
            products = productService.findByCategoryName(category);
        } else {
            products = productService.findAll();
        }

        return ProductResponse.from(products);
    }

    @GetMapping("/{productId}")
    ProductResponse getOne(@PathVariable Long productId) {
        var product = productService.findById(productId)
            .orElseThrow(() -> new ResourceNotFoundException(Product.class));

        return ProductResponse.from(product);
    }
}
