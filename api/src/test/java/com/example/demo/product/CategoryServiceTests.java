package com.example.demo.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTests {

    @InjectMocks
    private CategoryService service;

    @Mock
    private CategoryRepository repository;

    @Mock
    private Category category;

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(List.of(category));
        assertThat(service.findAll()).isNotEmpty();
    }

    @Test
    void findByName() {
        when(repository.findByName("foo")).thenReturn(List.of(category));
        assertThat(service.findByName("foo")).isNotEmpty();
    }
}
