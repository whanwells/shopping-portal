package com.example.demo.user.cart;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ForbiddenException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.product.ProductService;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/cart")
@Transactional
@RequiredArgsConstructor
public class CartController {

    private final CartItemService cartItemService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public List<CartItemResponse> get(@PathVariable Long userId, Principal principal) {
        ForbiddenException.throwIfPrincipalMismatch(principal, userId);

        // Does the user exist?
        if (!userService.existsById(userId)) {
            throw new ResourceNotFoundException(User.class);
        }

        return CartItemResponse.from(cartItemService.findByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<CartItemResponse> post(
        @PathVariable Long userId,
        @RequestBody CartPostRequest request,
        Principal principal
    ) {
        ForbiddenException.throwIfPrincipalMismatch(principal, userId);

        // Does the user exist?
        var user = userService.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException(User.class));

        // Does the product exist?
        var product = productService.findById(request.getProductId())
            .orElseThrow(() -> new BadRequestException("Product not valid"));

        // Add the item to the cart
        var cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        var cartItemId = cartItemService.save(cartItem);

        var location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/../users/{userId}/cart/{cartItemId}")
            .buildAndExpand(userId, cartItemId)
            .normalize()
            .toUri();

        return ResponseEntity.created(location).body(CartItemResponse.from(cartItem));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll(@PathVariable Long userId, Principal principal) {
        ForbiddenException.throwIfPrincipalMismatch(principal, userId);

        // Does the user exist?
        if (!userService.existsById(userId)) {
            throw new ResourceNotFoundException(User.class);
        }

        cartItemService.deleteByUserId(userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> deleteOne(@PathVariable Long userId, @PathVariable Long cartItemId, Principal principal) {
        ForbiddenException.throwIfPrincipalMismatch(principal, userId);

        System.out.println(cartItemId);

        // Does the cart item exist?
        var cartItem = cartItemService.findById(cartItemId)
            .orElseThrow(ResourceNotFoundException::new);

        // Does the user own cart the item?
        if (!cartItem.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException();
        }

        cartItemService.delete(cartItem);
        return ResponseEntity.noContent().build();
    }
}
