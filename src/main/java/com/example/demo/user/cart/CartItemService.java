package com.example.demo.user.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository repository;

    public Optional<CartItem> findById(Long itemId) {
        return repository.findById(itemId);
    }

    public List<CartItem> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId) {
        return repository.findByUserIdAndProductId(userId, productId);
    }

    public Long save(CartItem cartItem) {
        return repository.save(cartItem).getId();
    }

    public void delete(CartItem cartItem) {
        repository.delete(cartItem);
    }

    public void deleteByUserId(Long userId) {
        repository.deleteByUserId(userId);
    }
}
