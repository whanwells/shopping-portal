package com.example.demo.user.cart;

import com.example.demo.Fake;
import com.example.demo.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CartItemRepositoryTests {
    @Autowired
    private CartItemRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    private User user;

    @BeforeEach
    void setup() {
        user = entityManager.persist(Fake.user());
        var category = entityManager.persist(Fake.category());
        var product = entityManager.persist(Fake.product(category));
        entityManager.persist(Fake.item(user, product));
    }

    @Test
    void findByUserId() {
        assertThat(repository.findByUserId(user.getId())).isNotEmpty();
    }

    @Test
    void deleteAllByUserId() {
        repository.deleteByUserId(user.getId());
        assertThat(repository.findByUserId(user.getId())).isEmpty();
    }
}
