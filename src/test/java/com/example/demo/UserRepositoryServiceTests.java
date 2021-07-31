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

    @Mock
    private User user;

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserRepositoryService service;

    @Test
    void findsAllUsers() {
        when(repository.findAll()).thenReturn(List.of(user));
        assertThat(service.findAll()).hasSize(1);
    }

    @Test
    void findUsersById() {
        when(repository.findById(1L)).thenReturn(Optional.of(user));
        assertThat(service.findById(1L)).isPresent();
    }
}
