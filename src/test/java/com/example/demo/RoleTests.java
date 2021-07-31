package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class RoleTests {

    @Test
    void constructsWithNoArgs() {
        var role = new Role();

        assertSoftly(softly -> {
            softly.assertThat(role.getId()).isNull();
            softly.assertThat(role.getName()).isNull();
        });
    }

    @Test
    void constructsWithAllArgs() {
        var role = new Role(1L, "foo");

        assertSoftly(softly -> {
            softly.assertThat(role.getId()).isEqualTo(1);
            softly.assertThat(role.getName()).isEqualTo("foo");
        });
    }

    @Test
    void constructsWithoutId() {
        var role = new Role("foo");

        assertSoftly(softly -> {
            softly.assertThat(role.getId()).isNull();
            softly.assertThat(role.getName()).isEqualTo("foo");
        });
    }

    @Test
    void setsId() {
        var role = new Role();
        role.setId(1L);
        assertThat(role.getId()).isEqualTo(1);
    }

    @Test
    void setsName() {
        var role = new Role();
        role.setName("foo");
        assertThat(role.getName()).isEqualTo("foo");
    }
}
