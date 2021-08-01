package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    private static User createUser() {
        var user = new User();
        user.setEmail("foo@example.com");
        user.setPassword("bar");
        return user;
    }

    @Test
    void findsUsersByEmail() {
        entityManager.persist(createUser());
        assertThat(repository.findByEmail("foo@example.com")).isPresent();
    }
}
