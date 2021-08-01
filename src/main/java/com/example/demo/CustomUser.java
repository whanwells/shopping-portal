package com.example.demo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUser extends org.springframework.security.core.userdetails.User {

    private final long id;

    public CustomUser(long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
    }

    public static CustomUser from(User user) {
        return new CustomUser(user.getId(), user.getEmail(), user.getPassword(), mapRoles(user.getRoles()));
    }

    private static Collection<? extends GrantedAuthority> mapRoles(Collection<Role> roles) {
        return roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }
}
