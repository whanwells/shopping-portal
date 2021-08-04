package com.example.demo.user.order;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ForbiddenException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.product.ProductService;
import com.example.demo.user.UserService;
import com.example.demo.user.cart.ItemService;
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
    private final ItemService itemService;
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
        var items = itemService.findByUserId(userId);

        if (items.isEmpty()) {
            throw new BadRequestException("Cannot place an order with an empty cart");
        }

        // Let's begin a new order
        var order = new Order();
        order.setUser(user);

        // Make sure each product is in stock
        for (var item : items) {
            if (!item.getProduct().isStocked()) {
                throw new BadRequestException("Product '" + item.getProduct().getName() + "' is out of stock");
            }

            if (item.getQuantity() > item.getProduct().getQuantity()) {
                throw new BadRequestException("Product '" + item.getProduct().getName() + "' quantity exceeds stock");
            }

            // Reduce product quantity
            item.getProduct().reduceQuantityBy(item.getQuantity());
            productService.save(item.getProduct());

            // Create an order line and add it to the order
            var orderLine = new OrderLine();
            orderLine.setProduct(item.getProduct());
            orderLine.setQuantity(item.getQuantity());
            order.addLine(orderLine);
        }

        // Save the order
        var orderId = orderService.save(order);

        // Clear the user's cart
        itemService.deleteByUserId(userId);

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
