package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderRepositoryServiceTests {

    @InjectMocks
    private OrderRepositoryService service;

    @Mock
    private OrderRepository repository;

    @Mock
    private Order order;

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(List.of(order));
        assertThat(service.findAll()).hasSize(1);
    }

    @Test
    void findByUserId() {
        when(repository.findByUser_Id(1L)).thenReturn(List.of(order));
        assertThat(service.findByUserId(1L)).hasSize(1);
    }

    @Test
    void findByUserIdAndOrderId() {
        when(repository.findByUser_IdAndId(1L, 1L)).thenReturn(Optional.of(order));
        assertThat(service.findByUserIdAndOrderId(1L, 1L)).isPresent();
    }

    @Test
    void existsByOrderIdAndProjectId() {
        when(repository.existsByIdAndLines_Product_Id(1L, 1L)).thenReturn(true);
        assertThat(service.existsByOrderIdAndProductId(1L, 1L)).isTrue();
    }

    @Test
    void save() {
        when(repository.save(order)).thenReturn(order);
        assertThat(service.save(order)).isEqualTo(order);
    }
}
