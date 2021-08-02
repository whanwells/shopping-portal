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
    ProductRepository repository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void findByCategory_Name() {
        var category = new Category();
        category.setName("foo");
        entityManager.persist(category);

        var product = new Product();
        product.setName("bar");
        product.setReleaseDate(LocalDate.of(2000, 1, 1));
        product.setMsrp(9.99);
        product.setQuantity(5);
        product.setCategory(category);
        entityManager.persist(product);

        assertThat(repository.findByCategory_Name("foo")).hasSize(1);
    }
}
