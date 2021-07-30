package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTests {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ProductRepository repository;

    @Test
    void findsProductsByCategoryName() {
        var category = new Category("foo");
        var product = new Product(category, "bar", LocalDate.now(), 9.99, 5);
        entityManager.persist(category);
        entityManager.persist(product);
        assertThat(repository.findByCategory_Name("foo")).hasSize(1);
    }
}
