package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryServiceTests {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductRepositoryService service;

    private static Category createCategory() {
        var category = new Category();
        category.setId(1L);
        category.setName("foo");
        return category;
    }

    private static Product createProduct() {
        var product = new Product();
        product.setId(1L);
        product.setName("bar");
        product.setReleaseDate(LocalDate.of(2000, 1, 1));
        product.setMsrp(9.99);
        product.setQuantity(5);
        product.setCategory(createCategory());
        return product;
    }

    @Test
    void findsAllProducts() {
        when(repository.findAll()).thenReturn(List.of(createProduct()));
        assertThat(service.findAll()).hasSize(1);
    }

    @Test
    void findsProductsByCategoryName() {
        when(repository.findByCategory_Name("foo")).thenReturn(List.of(createProduct()));
        assertThat(service.findByCategoryName("foo")).hasSize(1);
    }

    @Test
    void findsProductById() {
        when(repository.findById(1L)).thenReturn(Optional.of(createProduct()));
        assertThat(service.findById(1L)).isPresent();
    }
}
