package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class RoleTests {

    @Test
    void constructor() {
        var role = new Role();

        assertSoftly(s -> {
            s.assertThat(role.getId()).isNull();
            s.assertThat(role.getName()).isNull();
            s.assertThat(role.getUsers()).isEmpty();
        });
    }
}
