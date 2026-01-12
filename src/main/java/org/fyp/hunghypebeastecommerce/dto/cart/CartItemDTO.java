package org.fyp.hunghypebeastecommerce.dto.cart;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CartItemDTO {

    private Long id;
    private Long variantId;
    private String productName;
    private String sku;
    private String size;
    private String color;
    private BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal subtotal;
    private Integer availableStock;
}
