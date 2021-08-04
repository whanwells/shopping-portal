package com.example.demo.user;

import com.example.demo.Fake;
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
        var role = entityManager.persist(Fake.role());
        assertThat(repository.findByName(role.getName())).isPresent();
    }
}
