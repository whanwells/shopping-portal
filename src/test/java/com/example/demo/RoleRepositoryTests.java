package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RoleRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RoleRepository repository;

    private static Role createRole() {
        var role = new Role();
        role.setName("foo");
        return role;
    }

    @Test
    void findsRolesByName() {
        entityManager.persist(createRole());
        assertThat(repository.findByName("foo")).isPresent();
    }
}
