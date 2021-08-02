package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryServiceTests {

    @InjectMocks
    private ProductRepositoryService service;

    @Mock
    private ProductRepository repository;

    @Mock
    private Product product;

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(List.of(product));
        assertThat(service.findAll()).hasSize(1);
    }

    @Test
    void findByCategory_Name() {
        when(repository.findByCategory_Name("foo")).thenReturn(List.of(product));
        assertThat(service.findByCategoryName("foo")).hasSize(1);
    }

    @Test
    void findById() {
        when(repository.findById(1L)).thenReturn(Optional.of(product));
        assertThat(service.findById(1L)).isPresent();
    }
}
