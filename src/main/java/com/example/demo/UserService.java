package com.example.demo;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(long id);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsById(long id);

    User save(User user);
}
