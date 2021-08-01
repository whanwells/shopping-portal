package com.example.demo;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> findAll();

    Optional<Role> findById(Long id);

    Optional<Role> findByName(String name);
}
