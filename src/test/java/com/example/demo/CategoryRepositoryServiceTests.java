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

    @InjectMocks
    private CategoryRepositoryService service;

    @Mock
    private CategoryRepository repository;

    @Mock
    private Category category;

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(List.of(category));
        assertThat(service.findAll()).hasSize(1);
    }

    @Test
    void findById() {
        when(repository.findById(1L)).thenReturn(Optional.of(category));
        assertThat(service.findById(1L)).isPresent();
    }
}
