package com.example.demo.user.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderTests {

    @Mock
    private OrderLine orderLine;

    @Test
    void addLine() {
        var order = new Order();
        order.addLine(orderLine);
        verify(orderLine).setOrder(order);
    }

    @Test
    void removeLine() {
        var order = new Order();
        order.removeLine(orderLine);
        verify(orderLine).setOrder(null);
    }
}
