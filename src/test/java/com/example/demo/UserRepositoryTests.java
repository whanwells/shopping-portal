package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTests {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    private long id;

    @BeforeEach
    void setup() {
        id = entityManager.persistAndGetId(Fake.user(), Long.class);
    }

    @Test
    void findByEmail() {
        assertThat(repository.findByEmail("foo@example.com")).isPresent();
    }

    @Test
    void existsByEmail() {
        assertThat(repository.existsByEmail("foo@example.com")).isTrue();
    }

    @Test
    void existsById() {
        assertThat(repository.existsById(id)).isTrue();
    }
}
