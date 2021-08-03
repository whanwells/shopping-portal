package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserTests {

    @Mock
    private Role role;

    @Mock
    private List<User> roleUsers;

    @Mock
    private Order order;

    @Test
    void constructorWithoutId() {
        var user = new User("foo@example.com", "bar");

        assertSoftly(s -> {
            s.assertThat(user.getEmail()).isEqualTo("foo@example.com");
            s.assertThat(user.getPassword()).isEqualTo("bar");
        });
    }

    @Test
    void addRole() {
        when(role.getUsers()).thenReturn(roleUsers);

        var user = new User();
        user.addRole(role);

        assertThat(user.getRoles()).contains(role);
        verify(roleUsers).add(user);
    }

    @Test
    void removeRole() {
        when(role.getUsers()).thenReturn(roleUsers);

        var user = new User();
        user.removeRole(role);

        assertThat(user.getRoles()).doesNotContain(role);
        verify(roleUsers).remove(user);
    }

    @Test
    void addOrder() {
        var user = new User();
        user.addOrder(order);
        verify(order).setUser(user);
    }

    @Test
    void removeOrder() {
        var user = new User();
        user.removeOrder(order);
        verify(order).setUser(null);
    }
}
