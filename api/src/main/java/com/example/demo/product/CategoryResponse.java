package com.example.demo.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class CategoryResponse {

    private final String category;
    private final List<ProductResponse> products;

    public static CategoryResponse from(Category category) {
        return new CategoryResponse(
            category.getName(),
            ProductResponse.from(category.getProducts())
        );
    }

    public static List<CategoryResponse> from(List<Category> categories) {
        return categories.stream().map(CategoryResponse::from).collect(Collectors.toList());
    }
}
