package com.example.demo.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByUserId(Long userId);

    Optional<Item> findByUserIdAndProductId(Long userId, Long productId);

    void deleteByUserId(Long userId);
}
