package com.example.demo.user.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public List<Order> findAll() {
        return repository.findAll();
    }

    public List<Order> findByUserId(long userId) {
        return repository.findByUser_Id(userId);
    }

    public Optional<Order> findByUserIdAndOrderId(long userId, long orderId) {
        return repository.findByUser_IdAndId(userId, orderId);
    }

    public Long save(Order order) {
        order.setDate(LocalDateTime.now());
        return repository.save(order).getId();
    }
}
