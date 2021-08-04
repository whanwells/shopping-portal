package com.example.demo.user;

import com.example.demo.Fake;
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

    private User user;

    @BeforeEach
    void setup() {
        user = entityManager.persist(Fake.user());
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
        assertThat(repository.existsById(user.getId())).isTrue();
    }
}
