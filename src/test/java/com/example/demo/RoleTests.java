package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class RoleTests {

    private final Role role = new Role();

    @Test
    void constructor() {
        assertSoftly(s -> {
            s.assertThat(role.getId()).isNull();
            s.assertThat(role.getName()).isNull();
            s.assertThat(role.getUsers()).isEmpty();
        });
    }

    @Test
    void setId() {
        role.setId(1L);
        assertThat(role.getId()).isEqualTo(1);
    }

    @Test
    void setName() {
        role.setName("foo");
        assertThat(role.getName()).isEqualTo("foo");
    }
}
