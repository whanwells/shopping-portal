package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderTests {

    @Mock
    private User user;

    @Mock
    private OrderLine line;

    @Test
    void from() {
        var order = Order.from(user);
        verify(user).addOrder(order);
    }

    @Test
    void addLine() {
        var order = new Order();
        order.addLine(line);
        verify(line).setOrder(order);
    }

    @Test
    void removeLine() {
        var order = new Order();
        order.removeLine(line);
        verify(line).setOrder(null);
    }

    @Test
    void isOpenWithNullDate() {
        var order = new Order();
        assertThat(order.isOpen()).isFalse();
    }

    @Test
    void isOpenWithNonNullDate() {
        var order = new Order();
        order.setDate(LocalDateTime.now());
        assertThat(order.isOpen()).isTrue();
    }
}
