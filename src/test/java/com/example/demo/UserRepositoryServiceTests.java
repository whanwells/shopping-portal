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
class UserRepositoryServiceTests {

    @InjectMocks
    private UserRepositoryService service;

    @Mock
    private UserRepository repository;

    @Mock
    private User user;

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(List.of(user));
        assertThat(service.findAll()).hasSize(1);
    }

    @Test
    void findById() {
        when(repository.findById(1L)).thenReturn(Optional.of(user));
        assertThat(service.findById(1L)).isPresent();
    }

    @Test
    void findByEmail() {
        when(repository.findByEmail("foo@example.com")).thenReturn(Optional.of(user));
        assertThat(service.findByEmail("foo@example.com")).isPresent();
    }

    @Test
    void existsByEmail() {
        when(repository.existsByEmail("foo@example.com")).thenReturn(true);
        assertThat(service.existsByEmail("foo@example.com")).isTrue();
    }

    @Test
    void existsById() {
        when(repository.existsById(1L)).thenReturn(true);
        assertThat(service.existsById(1L)).isTrue();
    }

    @Test
    void save() {
        when(repository.save(user)).thenReturn(user);
        assertThat(service.save(user)).isEqualTo(user);
    }
}
