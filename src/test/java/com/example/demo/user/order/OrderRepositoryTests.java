package com.example.demo.user.order;

import com.example.demo.Fake;
import com.example.demo.user.User;
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

    private User user;
    private Order order;

    @BeforeEach
    void setup() {
        user = entityManager.persist(Fake.user());
        order = entityManager.persist(Fake.order(user));
    }

    @Test
    void findByUser_Id() {
        assertThat(repository.findByUser_Id(user.getId())).isNotEmpty();
    }

    @Test
    void findByUser_IdAndId() {
        assertThat(repository.findByUser_IdAndId(user.getId(), order.getId())).isPresent();
    }
}
