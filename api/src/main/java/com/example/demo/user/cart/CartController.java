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

    @PutMapping("/{productId}")
    public CartItemResponse put(
        @PathVariable Long userId,
        @PathVariable Long productId,
        @RequestBody CartItemPutRequest request,
        Principal principal
    ) {
        ForbiddenException.throwIfPrincipalMismatch(principal, userId);

        // Does the user exist?
        var user = userService.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException(User.class));

        // Does the product exist?
        var product = productService.findById(productId)
            .orElseThrow(() -> new BadRequestException("Product not valid"));

        // Is the quantity valid?
        if (request.getQuantity() < 1) {
            throw new BadRequestException("Quantity must be above 0");
        }

        // Does the product already exist in the cart?
        var item = cartItemService.findByUserIdAndProductId(userId, productId)
            .orElseGet(() -> {
                // Nope, create a new item
                var newItem = new CartItem();
                newItem.setUser(user);
                newItem.setProduct(product);
                return newItem;
            });

        item.setQuantity(request.getQuantity());
        cartItemService.save(item);
        return CartItemResponse.from(item);
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

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteOne(@PathVariable Long userId, @PathVariable Long productId, Principal principal) {
        ForbiddenException.throwIfPrincipalMismatch(principal, userId);

        // Does the user/product exist?
        var item = cartItemService.findByUserIdAndProductId(userId, productId)
            .orElseThrow(ResourceNotFoundException::new);

        // Does the user own the item?
        if (!item.getUser().getId().equals(userId)) {
            throw new ForbiddenException();
        }

        cartItemService.delete(item);
        return ResponseEntity.noContent().build();
    }
}