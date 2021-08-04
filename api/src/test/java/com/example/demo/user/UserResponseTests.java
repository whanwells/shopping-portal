package com.example.demo.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserResponseTests {

    @Mock
    private User user;

    @Mock
    private Role role;

    @Test
    void from() {
        when(user.getId()).thenReturn(1L);
        when(user.getEmail()).thenReturn("foo@example.com");
        when(user.getRoles()).thenReturn(List.of(role));

        var response = UserResponse.from(user);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getEmail()).isEqualTo("foo@example.com");
        assertThat(response.getRoles()).isNotEmpty();
    }
}
