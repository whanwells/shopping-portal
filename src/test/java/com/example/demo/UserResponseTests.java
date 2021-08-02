package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserResponseTests {

    @Mock
    private User user;

    @Mock
    private Role role;

    @BeforeEach
    void setupUser() {
        when(user.getId()).thenReturn(1L);
        when(user.getEmail()).thenReturn("foo@example.com");
        when(user.getRoles()).thenReturn(List.of(role));
    }

    @BeforeEach
    void setupRole() {
        when(role.getId()).thenReturn(2L);
        when(role.getName()).thenReturn("bar");
    }

    @Test
    void fromUser() {
        var response = UserResponse.from(user);

        assertSoftly(s -> {
            s.assertThat(response.getId()).isEqualTo(1);
            s.assertThat(response.getEmail()).isEqualTo("foo@example.com");
            s.assertThat(response.getRoles()).hasSize(1);
        });
    }

    @Test
    void fromUserList() {
        var response = UserResponse.from(List.of(user));

        assertSoftly(s -> {
            s.assertThat(response).hasSize(1);
            s.assertThat(response.get(0).getId()).isEqualTo(1);
            s.assertThat(response.get(0).getEmail()).isEqualTo("foo@example.com");
            s.assertThat(response.get(0).getRoles()).hasSize(1);
        });
    }
}
