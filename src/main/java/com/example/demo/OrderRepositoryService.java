package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderRepositoryService implements OrderService {

    private final OrderRepository repository;

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Order> findByUserId(long userId) {
        return repository.findByUser_Id(userId);
    }

    @Override
    public Optional<Order> findByUserIdAndOrderId(long userId, long orderId) {
        return repository.findByUser_IdAndId(userId, orderId);
    }

    @Override
    public boolean existsByOrderIdAndProductId(long orderId, long productId) {
        return repository.existsByIdAndLines_Product_Id(orderId, productId);
    }

    @Override
    public Order save(Order order) {
        return repository.save(order);
    }
}
