package com.example.demo.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByName(String name);
}