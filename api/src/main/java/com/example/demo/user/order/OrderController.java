package com.example.demo.user.order;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ForbiddenException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.product.ProductService;
import com.example.demo.user.UserService;
import com.example.demo.user.cart.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/orders")
@Transactional
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final CartItemService cartItemService;
    private final ProductService productService;

    @GetMapping
    public List<OrderResponse> getAll(@PathVariable long userId, Principal principal) {
        ForbiddenException.throwIfPrincipalMismatch(principal, userId);

        if (!userService.existsById(userId)) {
            throw new ResourceNotFoundException(Order.class);
        }

        return OrderResponse.from(orderService.findByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(Principal principal, @PathVariable long userId) {
        ForbiddenException.throwIfPrincipalMismatch(principal, userId);

        // Does the user exist?
        var user = userService.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException(Order.class));

        // Does the user have items in their cart?
        var items = cartItemService.findByUserId(userId);

        if (items.isEmpty()) {
            throw new BadRequestException("Cannot place an order with an empty cart");
        }

        // Let's begin a new order
        var order = new Order();
        order.setUser(user);

        for (var item : items) {
            // Don't add out of stock items
            if (!item.getProduct().isStocked()) {
                continue;
            }

            // Reduce product quantity
            item.getProduct().reduceQuantityBy(1);
            productService.save(item.getProduct());

            // Create an order line and add it to the order
            var orderLine = new OrderLine();
            orderLine.setProduct(item.getProduct());
            order.addLine(orderLine);
        }

        // Save the order
        var orderId = orderService.save(order);

        // Clear the user's cart
        cartItemService.deleteByUserId(userId);

        var location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{orderId}")
            .buildAndExpand(orderId)
            .toUri();

        return ResponseEntity.created(location).body(OrderResponse.from(order));
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOne(@PathVariable long userId, @PathVariable long orderId, Principal principal) {
        ForbiddenException.throwIfPrincipalMismatch(principal, userId);

        var order = orderService.findByUserIdAndOrderId(userId, orderId)
            .orElseThrow(() -> new ResourceNotFoundException(Order.class));

        return OrderResponse.from(order);
    }
}
