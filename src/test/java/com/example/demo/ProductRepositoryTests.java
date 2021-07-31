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

    private static Category createCategory() {
        var category = new Category();
        category.setName("foo");
        return category;
    }

    private static Product createProduct(Category category) {
        var product = new Product();
        product.setName("bar");
        product.setReleaseDate(LocalDate.of(2000, 1, 1));
        product.setMsrp(9.99);
        product.setQuantity(5);
        product.setCategory(category);
        return product;
    }

    @Test
    void findsProductsByCategoryName() {
        var category = createCategory();
        var product = createProduct(category);
        entityManager.persist(category);
        entityManager.persist(product);
        assertThat(repository.findByCategory_Name("foo")).hasSize(1);
    }
}
