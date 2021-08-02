package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserTests {

    @Mock
    private User user;

    @Mock
    private Role role;

    @Test
    void from() {
        when(user.getId()).thenReturn(1L);
        when(user.getEmail()).thenReturn("foo@example.com");
        when(user.getPassword()).thenReturn("bar");
        when(user.getRoles()).thenReturn(List.of(role));
        when(role.getName()).thenReturn("baz");

        var userDetails = CustomUser.from(user);

        assertSoftly(softly -> {
            softly.assertThat(userDetails.getId()).isEqualTo(1);
            softly.assertThat(userDetails.getUsername()).isEqualTo("foo@example.com");
            softly.assertThat(userDetails.getPassword()).isEqualTo("bar");
            softly.assertThat(
                userDetails
                    .getAuthorities()
                    .stream()
                    .anyMatch(a -> a.getAuthority().equals("baz"))
            ).isTrue();
        });
    }
}
