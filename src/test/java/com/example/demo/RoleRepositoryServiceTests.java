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
class RoleRepositoryServiceTests {

    @Mock
    private Role role;

    @Mock
    private RoleRepository repository;

    @InjectMocks
    private RoleRepositoryService service;

    @Test
    void findsAllRoles() {
        when(repository.findAll()).thenReturn(List.of(role));
        assertThat(service.findAll()).hasSize(1);
    }

    @Test
    void findsRoleById() {
        when(repository.findById(1L)).thenReturn(Optional.of(role));
        assertThat(service.findById(1L)).isPresent();
    }

    @Test
    void findsRolesByName() {
        when(repository.findByName("foo")).thenReturn(Optional.of(role));
        assertThat(service.findByName("foo")).isPresent();
    }
}
