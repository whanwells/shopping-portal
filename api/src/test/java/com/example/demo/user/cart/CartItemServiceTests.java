package com.example.demo.user.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartItemServiceTests {

    @InjectMocks
    private CartItemService service;

    @Mock
    private CartItemRepository repository;

    @Mock
    private CartItem cartItem;

    @Test
    void findById() {
        when(repository.findById(1L)).thenReturn(Optional.of(cartItem));
        assertThat(service.findById(1L)).isPresent();
    }

    @Test
    void findByUserId() {
        when(repository.findByUserId(1L)).thenReturn(List.of(cartItem));
        assertThat(service.findByUserId(1L)).isNotEmpty();
    }

    @Test
    void save() {
        when(repository.save(cartItem)).thenReturn(cartItem);
        when(cartItem.getId()).thenReturn(1L);
        assertThat(service.save(cartItem)).isEqualTo(cartItem.getId());
    }

    @Test
    void delete() {
        service.delete(cartItem);
        verify(repository).delete(cartItem);
    }

    @Test
    void deleteByUserId() {
        service.deleteByUserId(1L);
        verify(repository).deleteByUserId(1L);
    }
}
