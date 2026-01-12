package org.fyp.hunghypebeastecommerce.dto.cart;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CartDTO {

    private UUID id;
    private String sessionId;
    private List<CartItemDTO> items;
    private Integer totalItems;
    private BigDecimal totalAmount;
}
