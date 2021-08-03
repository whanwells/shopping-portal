package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<OrderResponse> getAll() {
        return OrderResponse.from(orderService.findAll());
    }

    @GetMapping("/{userId}")
    @Transactional
    public List<OrderResponse> getByUserId(Principal principal, @PathVariable long userId) {
        ForbiddenException.throwIfPrincipalMismatch(principal, String.valueOf(userId));

        if (!userService.existsById(userId)) {
            throw new ResourceNotFoundException(Order.class);
        }

        return OrderResponse.from(orderService.findByUserId(userId));
    }

    @PostMapping("/{userId}")
    @Transactional
    public ResponseEntity<OrderResponse> create(Principal principal, @PathVariable long userId) {
        ForbiddenException.throwIfPrincipalMismatch(principal, String.valueOf(userId));

        var user = userService.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException(Order.class));

        var order = orderService.save(Order.from(user));

        var location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{orderId}")
            .buildAndExpand(order.getId())
            .toUri();

        return ResponseEntity.created(location).body(OrderResponse.from(order));
    }

    @GetMapping("/{userId}/{orderId}")
    public OrderResponse getByUserIdAndOrderId(Principal principal, @PathVariable long userId, @PathVariable long orderId) {
        ForbiddenException.throwIfPrincipalMismatch(principal, String.valueOf(userId));

        var order = orderService.findByUserIdAndOrderId(userId, orderId)
            .orElseThrow(() -> new ResourceNotFoundException(Order.class));

        return OrderResponse.from(order);
    }
}
