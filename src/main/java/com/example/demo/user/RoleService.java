package com.example.demo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;

    public Optional<Role> findByName(String name) {
        return repository.findByName(name);
    }
}
