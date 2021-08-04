package com.example.demo.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class ProductResponse {
    private final long id;
    private final String name;
    private final LocalDate releaseDate;
    private final double msrp;
    private final boolean stocked;

    public static ProductResponse from(Product product) {
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getReleaseDate(),
            product.getMsrp(),
            product.isStocked()
        );
    }

    public static List<ProductResponse> from(List<Product> products) {
        return products.stream().map(ProductResponse::from).collect(Collectors.toList());
    }
}
