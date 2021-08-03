package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {

    Optional<OrderLine> findByOrder_User_IdAndOrder_IdAndId(long orderId, long userId, long lineId);
}
