package com.example.demo.product;

import com.example.demo.Fake;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CategoryRepositoryTests {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findByCategory_Name() {
        var category = entityManager.persist(Fake.category());
        assertThat(repository.findByName(category.getName())).isNotEmpty();
    }
}
