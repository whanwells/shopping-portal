package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductRepositoryService implements ProductService {

    private final ProductRepository repository;

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Product> findByCategoryName(String name) {
        return repository.findByCategory_Name(name);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }
}
