package com.example.demo;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAll();

    List<Order> findByUserId(long id);

    boolean existsByUserIdAndOpen(long userId);

    Optional<Order> findByUserIdAndOrderId(long userId, long orderId);

    boolean existsByOrderIdAndProductId(long orderId, long productId);

    Order save(Order order);
}
