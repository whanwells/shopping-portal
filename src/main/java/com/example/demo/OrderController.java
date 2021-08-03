package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;
    private final OrderLineService orderLineService;
    private final ProductService productService;

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

        if (orderService.existsByUserIdAndOpen(userId)) {
            throw new BadRequestException("There can only be one open order per user");
        }

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

    @PostMapping("/{userId}/{orderId}")
    @Transactional
    public ResponseEntity<OrderLineResponse> addLine(
        Principal principal,
        @PathVariable long userId,
        @PathVariable long orderId,
        @Valid @RequestBody OrderLinePostRequest request
    ) {
        ForbiddenException.throwIfPrincipalMismatch(principal, String.valueOf(userId));

        var order = orderService.findByUserIdAndOrderId(userId, orderId)
            .orElseThrow(() -> new ResourceNotFoundException(Order.class));

        if (orderService.existsByOrderIdAndProductId(orderId, request.getProductId())) {
            throw new BadRequestException("A line with the given product ID already exists");
        }

        var product = productService.findById(request.getProductId())
            .orElseThrow(() -> new BadRequestException("The given product ID invalid"));

        var orderLine = orderLineService.save(OrderLine.of(order, product, request.getQuantity()));

        var location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{itemId}")
            .buildAndExpand(orderLine.getId())
            .toUri();

        return ResponseEntity.created(location).body(OrderLineResponse.from(orderLine));
    }

    @PatchMapping("/{userId}/{orderId}/{lineId}")
    @Transactional
    public ResponseEntity<OrderLineResponse> updateLine(
        Principal principal,
        @PathVariable long userId,
        @PathVariable long orderId,
        @PathVariable long lineId,
        @RequestBody OrderLinePatchRequest request
    ) {
        ForbiddenException.throwIfPrincipalMismatch(principal, String.valueOf(userId));

        var orderLine = orderLineService.findByUserIdAndOrderIdAndLineId(userId, orderId, lineId)
            .orElseThrow(() -> new ResourceNotFoundException(OrderLine.class));

        orderLine.setQuantity(request.getQuantity());

        orderLineService.save(orderLine);

        return ResponseEntity.ok(OrderLineResponse.from(orderLine));
    }

    @DeleteMapping("/{userId}/{orderId}/{lineId}")
    @Transactional
    public ResponseEntity<Void> delete(
        Principal principal,
        @PathVariable long userId,
        @PathVariable long orderId,
        @PathVariable long lineId
    ) {
        ForbiddenException.throwIfPrincipalMismatch(principal, String.valueOf(userId));

        var orderLine = orderLineService.findByUserIdAndOrderIdAndLineId(userId, orderId, lineId)
            .orElseThrow(() -> new ResourceNotFoundException(OrderLine.class));

        orderLineService.delete(orderLine);

        return ResponseEntity.noContent().build();
    }
}
