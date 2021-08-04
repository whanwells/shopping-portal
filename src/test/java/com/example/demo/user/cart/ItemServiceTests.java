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
class ItemServiceTests {

    @InjectMocks
    private ItemService service;

    @Mock
    private ItemRepository repository;

    @Mock
    private Item item;

    @Test
    void findById() {
        when(repository.findById(1L)).thenReturn(Optional.of(item));
        assertThat(service.findById(1L)).isPresent();
    }

    @Test
    void findByUserId() {
        when(repository.findByUserId(1L)).thenReturn(List.of(item));
        assertThat(service.findByUserId(1L)).isNotEmpty();
    }

    @Test
    void findByUserIdAndProductId() {
        when(repository.findByUserIdAndProductId(1L, 1L)).thenReturn(Optional.of(item));
        assertThat(service.findByUserIdAndProductId(1L, 1L)).isPresent();
    }

    @Test
    void save() {
        when(repository.save(item)).thenReturn(item);
        when(item.getId()).thenReturn(1L);
        assertThat(service.save(item)).isEqualTo(item.getId());
    }

    @Test
    void delete() {
        service.delete(item);
        verify(repository).delete(item);
    }

    @Test
    void deleteByUserId() {
        service.deleteByUserId(1L);
        verify(repository).deleteByUserId(1L);
    }
}
