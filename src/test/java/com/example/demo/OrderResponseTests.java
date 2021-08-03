package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderResponseTests {

    @Mock
    private Order order;

    @Mock User user;

    private final LocalDateTime date = LocalDateTime.now();

    @BeforeEach
    void setup() {
        when(order.getId()).thenReturn(1L);
        when(order.getDate()).thenReturn(date);
        when(order.getUser()).thenReturn(user);
        when(user.getId()).thenReturn(2L);
        when(user.getEmail()).thenReturn("foo@example.com");
    }

    @Test
    void fromOrder() {
        var response = OrderResponse.from(order);

        assertSoftly(s -> {
            s.assertThat(response.getId()).isEqualTo(1L);
            s.assertThat(response.getUser().getId()).isEqualTo(2L);
            s.assertThat(response.getUser().getEmail()).isEqualTo("foo@example.com");
            s.assertThat(response.getDate()).isEqualTo(date);
        });
    }

    @Test
    void fromOrderList() {
        var response = OrderResponse.from(List.of(order));

        assertSoftly(s -> {
            s.assertThat(response).hasSize(1);
            s.assertThat(response.get(0).getId()).isEqualTo(1L);
            s.assertThat(response.get(0).getUser().getId()).isEqualTo(2L);
            s.assertThat(response.get(0).getUser().getEmail()).isEqualTo("foo@example.com");
            s.assertThat(response.get(0).getDate()).isEqualTo(date);
        });
    }
}
