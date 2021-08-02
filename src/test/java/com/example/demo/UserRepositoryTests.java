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

    @BeforeEach
    void setup() {
        entityManager.persist(new User("foo@example.com", "bar"));
    }

    @Test
    void findByEmail() {
        assertThat(repository.findByEmail("foo@example.com")).isPresent();
    }

    @Test
    void existsByEmail() {
        assertThat(repository.existsByEmail("foo@example.com")).isTrue();
    }
}
