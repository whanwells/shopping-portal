package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderLineRepositoryService implements OrderLineService {

    private final OrderLineRepository repository;

    @Override
    public Optional<OrderLine> findByUserIdAndOrderIdAndLineId(long userId, long orderId, long lineId) {
        return repository.findByOrder_User_IdAndOrder_IdAndId(userId, orderId, lineId);
    }

    @Override
    public OrderLine save(OrderLine orderLine) {
        return repository.save(orderLine);
    }

    @Override
    public void delete(OrderLine orderLine) {
        repository.delete(orderLine);
    }
}
