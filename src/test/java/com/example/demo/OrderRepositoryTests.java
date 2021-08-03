package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrderRepositoryTests {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    private long userId;
    private long orderId;

    @BeforeEach
    void setup() {
        var user = new User("foo@example.com", "bar");
        userId = entityManager.persistAndGetId(user, Long.class);
        orderId = entityManager.persistAndGetId(Order.from(user), Long.class);
    }

    @Test
    void findByUser_Id() {
        assertThat(repository.findByUser_Id(userId)).hasSize(1);
    }

    @Test
    void findByUser_IdAndId() {
        assertThat(repository.findByUser_IdAndId(userId, orderId)).isPresent();
    }
}
