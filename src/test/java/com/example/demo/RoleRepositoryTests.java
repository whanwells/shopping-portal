package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RoleRepositoryTests {

    @Autowired
    private RoleRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findByName() {
        var role = new Role();
        role.setName("foo");
        entityManager.persist(role);
        assertThat(repository.findByName("foo")).isPresent();
    }
}
