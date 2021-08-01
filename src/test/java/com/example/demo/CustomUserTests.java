package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserTests {

    @Mock
    private User user;

    @Mock
    private Role role;

    @Test
    void createUserDetailsFromUser() {
        when(user.getId()).thenReturn(1L);
        when(user.getEmail()).thenReturn("foo@example.com");
        when(user.getPassword()).thenReturn("bar");
        when(user.getRoles()).thenReturn(Set.of(role));
        when(role.getName()).thenReturn("baz");

        var userDetails = CustomUser.from(user);

        assertSoftly(softly -> {
            softly.assertThat(userDetails.getId()).isEqualTo(1);
            softly.assertThat(userDetails.getUsername()).isEqualTo("foo@example.com");
            softly.assertThat(userDetails.getPassword()).isEqualTo("bar");
            softly.assertThat(hasAuthority(userDetails)).isTrue();
        });
    }

    private static boolean hasAuthority(CustomUser customUser) {
        return customUser
            .getAuthorities()
            .stream()
            .anyMatch(a -> a.getAuthority().equals("baz"));
    }
}
