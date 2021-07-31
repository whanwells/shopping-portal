package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class RoleTests {

    private final Role role = new Role();

    @Test
    void constructs() {
        assertSoftly(softly -> {
            softly.assertThat(role.getId()).isNull();
            softly.assertThat(role.getName()).isNull();
            softly.assertThat(role.getUsers()).isEmpty();
        });
    }

    @Test
    void setsId() {
        role.setId(1L);
        assertThat(role.getId()).isEqualTo(1);
    }

    @Test
    void setsName() {
        role.setName("foo");
        assertThat(role.getName()).isEqualTo("foo");
    }
}
