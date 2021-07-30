package com.example.demo;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    List<Product> findByCategoryName(String name);

    Optional<Product> findById(Long id);
}
