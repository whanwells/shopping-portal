package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTests {

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private UserService userService;

    @Mock
    private User user;

    @Test
    void loadUserByUsername() {
        when(user.getEmail()).thenReturn("foo@example.com");
        when(user.getPassword()).thenReturn("bar");
        when(user.getRoles()).thenReturn(Set.of());
        when(userService.findByEmail("foo@example.com")).thenReturn(Optional.of(user));

        assertThatNoException()
            .isThrownBy(() -> customUserDetailsService.loadUserByUsername("foo@example.com"));
    }

    @Test
    void loadUserByUsernameWithInvalidEmail() {
        when(userService.findByEmail("foo@example.com")).thenReturn(Optional.empty());

        assertThatExceptionOfType(UsernameNotFoundException.class)
            .isThrownBy(() -> customUserDetailsService.loadUserByUsername("foo@example.com"))
            .withMessage("User with email 'foo@example.com' not found");
    }
}
