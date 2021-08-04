package com.example.demo.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public List<Product> findByCategoryName(String name) {
        return repository.findByCategory_Name(name);
    }

    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    public Long save(Product product) {
        return repository.save(product).getId();
    }
}
