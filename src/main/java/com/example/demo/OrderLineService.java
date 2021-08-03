package com.example.demo;

import java.util.Optional;

public interface OrderLineService {

    Optional<OrderLine> findByUserIdAndOrderIdAndLineId(long userId, long orderId, long lineId);

    OrderLine save(OrderLine orderLine);

    void delete(OrderLine orderLine);
}
