package com.example.demo;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAll();

    List<Order> findByUserId(long id);

    Optional<Order> findByUserIdAndOrderId(long userId, long orderId);

    Order save(Order order);
}
