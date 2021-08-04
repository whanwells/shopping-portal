package com.example.demo.registration;

import com.example.demo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

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

        // All users get ROLE_USER as a base
        var role = roleService.findByName("ROLE_USER")
            .orElseThrow(() -> new InternalServerException("Unable to assign ROLE_USER"));

        // Create the new user
        var user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.addRole(role);

        var userId = userService.save(user);

        var location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/../users/{userId}")
            .buildAndExpand(userId)
            .normalize()
            .toUri();

        return ResponseEntity
            .created(location)
            .body(new RegistrationResponse(userId, user.getEmail()));
    }
}
