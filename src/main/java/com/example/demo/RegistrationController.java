package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/api/register")
    @Transactional
    public ResponseEntity<RegistrationResponse> register(@Valid @RequestBody RegistrationRequest request) {
        if (userService.existsByEmail(request.getEmail())) {
            throw new BadRequestException("A user with that email already exists");
        }

        var user = userService.save(createUser(request));

        return ResponseEntity
            .created(createLocation(user))
            .body(RegistrationResponse.from(user));
    }

    private User createUser(RegistrationRequest request) {
        var user = new User(request.getEmail(), passwordEncoder.encode(request.getPassword()));
        user.addRole(getUserRole());
        return user;
    }

    private Role getUserRole() {
        return roleService.findByName("ROLE_USER")
            .orElseThrow(() -> new InternalServerException("Unable to assign ROLE_USER"));
    }

    private static URI createLocation(User user) {
        return ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/../users/{id}")
            .buildAndExpand(user.getId())
            .normalize()
            .toUri();
    }
}
