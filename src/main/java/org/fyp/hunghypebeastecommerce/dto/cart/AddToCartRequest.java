package org.fyp.hunghypebeastecommerce.dto.cart;

import lombok.Data;

@Data
public class AddToCartRequest {

    private Long variantId;
    private Integer quantity;
}
