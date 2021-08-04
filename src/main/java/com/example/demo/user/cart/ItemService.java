package com.example.demo.user.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    public Optional<Item> findById(Long itemId) {
        return repository.findById(itemId);
    }

    public List<Item> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public Optional<Item> findByUserIdAndProductId(Long userId, Long productId) {
        return repository.findByUserIdAndProductId(userId, productId);
    }

    public Item save(Item item) {
        return repository.save(item);
    }

    public void delete(Item item) {
        repository.delete(item);
    }

    public void deleteByUserId(Long userId) {
        repository.deleteByUserId(userId);
    }
}
