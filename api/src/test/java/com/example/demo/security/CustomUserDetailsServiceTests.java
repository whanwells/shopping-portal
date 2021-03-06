package com.example.demo.security;

import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

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
        when(user.getRoles()).thenReturn(List.of());
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
