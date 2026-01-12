package org.fyp.hunghypebeastecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.fyp.hunghypebeastecommerce.dto.ResponseObject;
import org.fyp.hunghypebeastecommerce.dto.cart.AddToCartRequest;
import org.fyp.hunghypebeastecommerce.dto.cart.CartDTO;
import org.fyp.hunghypebeastecommerce.dto.cart.UpdateCartItemRequest;
import org.fyp.hunghypebeastecommerce.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<ResponseObject<CartDTO>> getCart(
            @RequestHeader("X-Session-Id") String sessionId
    ) {
        CartDTO cart = cartService.getCart(sessionId);
        return ResponseEntity.ok(ResponseObject.success("Cart retrieved successfully", cart));
    }

    @PostMapping("/items")
    public ResponseEntity<ResponseObject<CartDTO>> addToCart(
            @RequestHeader("X-Session-Id") String sessionId,
            @RequestBody AddToCartRequest request
    ) {
        CartDTO cart = cartService.addToCart(sessionId, request);
        return ResponseEntity.ok(ResponseObject.success("Item added to cart", cart));
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<ResponseObject<CartDTO>> updateCartItem(
            @RequestHeader("X-Session-Id") String sessionId,
            @PathVariable Long itemId,
            @RequestBody UpdateCartItemRequest request
    ) {
        CartDTO cart = cartService.updateCartItem(sessionId, itemId, request);
        return ResponseEntity.ok(ResponseObject.success("Cart item updated", cart));
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<ResponseObject<CartDTO>> removeCartItem(
            @RequestHeader("X-Session-Id") String sessionId,
            @PathVariable Long itemId
    ) {
        CartDTO cart = cartService.removeCartItem(sessionId, itemId);
        return ResponseEntity.ok(ResponseObject.success("Item removed from cart", cart));
    }
}
