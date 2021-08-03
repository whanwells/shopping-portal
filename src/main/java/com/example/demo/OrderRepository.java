package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser_Id(long userId);

    boolean existsByUser_IdAndDateNull(long userId);

    Optional<Order> findByUser_IdAndId(long userId, long orderId);

    boolean existsByIdAndLines_Product_Id(long orderId, long productId);
}