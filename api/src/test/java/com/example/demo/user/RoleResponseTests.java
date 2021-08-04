package com.example.demo.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleResponseTests {

    @Mock
    private Role role;

    @Test
    void from() {
        when(role.getId()).thenReturn(1L);
        when(role.getName()).thenReturn("foo");

        var response = RoleResponse.from(role);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("foo");
    }
}
