package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserTests {

    @Mock
    private Role role;

    @Mock
    private Set<User> roleUsers;

    @Test
    void constructsWithoutArgs() {
        var user = new User();

        assertSoftly(softly -> {
            softly.assertThat(user.getId()).isNull();
            softly.assertThat(user.getEmail()).isNull();
            softly.assertThat(user.getPassword()).isNull();
            softly.assertThat(user.getRoles()).isEmpty();
        });
    }

    @Test
    void constructsWithoutId() {
        var user = new User("foo@example.com", "bar");

        assertSoftly(softly -> {
            softly.assertThat(user.getId()).isNull();
            softly.assertThat(user.getEmail()).isEqualTo("foo@example.com");
            softly.assertThat(user.getPassword()).isEqualTo("bar");
            softly.assertThat(user.getRoles()).isEmpty();
        });
    }

    @Test
    void setsId() {
        var user = new User();
        user.setId(1L);
        assertThat(user.getId()).isEqualTo(1);
    }

    @Test
    void setsEmail() {
        var user = new User();
        user.setEmail("foo@example.com");
        assertThat(user.getEmail()).isEqualTo("foo@example.com");
    }

    @Test
    void setsPassword() {
        var user = new User();
        user.setPassword("bar");
        assertThat(user.getPassword()).isEqualTo("bar");
    }

    @Test
    void addsRoles() {
        when(role.getUsers()).thenReturn(roleUsers);

        var user = new User();
        user.addRole(role);

        assertSoftly(softly -> {
            softly.assertThat(user.getRoles()).contains(role);
            verify(roleUsers).add(user);
        });
    }

    @Test
    void removesRoles() {
        when(role.getUsers()).thenReturn(roleUsers);

        var user = new User();
        user.removeRole(role);

        assertSoftly(softly -> {
            softly.assertThat(user.getRoles()).doesNotContain(role);
            verify(roleUsers).remove(user);
        });
    }
}
