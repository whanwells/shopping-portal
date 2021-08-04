package com.example.demo;

import com.example.demo.product.Category;
import com.example.demo.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrderRepositoryTests {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    private Order order;
    private long userId;
    private long orderId;

    @BeforeEach
    void setup() {
        var user = Fake.user();
        userId = entityManager.persistAndGetId(user, Long.class);

        order = Order.from(user);
        orderId = entityManager.persistAndGetId(order, Long.class);
    }

    @Test
    void findByUser_Id() {
        assertThat(repository.findByUser_Id(userId)).hasSize(1);
    }

    @Test
    void findByUser_IdAndId() {
        assertThat(repository.findByUser_IdAndId(userId, orderId)).isPresent();
    }

    @Test
    void existsByIdAndLines_Product_Id() {
        var category = new Category();
        category.setName("foo");
        entityManager.persist(category);

        var product = new Product();
        product.setName("bar");
        product.setReleaseDate(LocalDate.now());
        product.setMsrp(9.99);
        product.setQuantity(1);
        product.setCategory(category);
        var productId = entityManager.persistAndGetId(product, Long.class);

        var line = new OrderLine();
        line.setOrder(order);
        line.setProduct(product);
        line.setQuantity(1);
        entityManager.persist(line);

        assertThat(repository.existsByIdAndLines_Product_Id(orderId, productId)).isTrue();
    }
}
