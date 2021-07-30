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
class CategoryRepositoryServiceTests {

    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryRepositoryService service;

    @Test
    void findsAllCategories() {
        when(repository.findAll()).thenReturn(List.of(createCategory()));
        assertThat(service.findAll()).hasSize(1);
    }

    @Test
    void findsCategoryById() {
        when(repository.findById(1L)).thenReturn(Optional.of(createCategory()));
        assertThat(service.findById(1L)).isPresent();
    }

    private static Category createCategory() {
        return new Category(1L, "foo");
    }
}
