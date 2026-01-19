package org.fyp.hunghypebeastecommerce.dto.checkout;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private Long id;
    private Long variantId;
    private String productName;
    private String variantSku;
    private String variantSize;
    private String variantColor;
    private BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal subtotal;
}
