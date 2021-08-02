package com.example.demo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class CategoryResponse {

    private final long id;
    private final String name;

    public static CategoryResponse from(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }

    public static List<CategoryResponse> from(List<Category> categories) {
        return categories.stream().map(CategoryResponse::from).collect(Collectors.toList());
    }
}
