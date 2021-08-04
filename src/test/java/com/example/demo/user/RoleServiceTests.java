package com.example.demo.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleServiceTests {

    @InjectMocks
    private RoleService service;

    @Mock
    private RoleRepository repository;

    @Mock
    private Role role;

    @Test
    void findByName() {
        when(repository.findByName("foo")).thenReturn(Optional.of(role));
        assertThat(service.findByName("foo")).isPresent();
    }
}
