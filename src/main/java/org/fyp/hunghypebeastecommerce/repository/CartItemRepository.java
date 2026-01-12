package org.fyp.hunghypebeastecommerce.repository;

import org.fyp.hunghypebeastecommerce.entity.Cart;
import org.fyp.hunghypebeastecommerce.entity.CartItem;
import org.fyp.hunghypebeastecommerce.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartAndVariant(Cart cart, ProductVariant variant);
}
