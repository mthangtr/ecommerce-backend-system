package org.fyp.hunghypebeastecommerce.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariantDTO {
    private Long id;
    private String sku;
    private String size;
    private String color;
    private BigDecimal price;
    private Integer availableStock;
    private Boolean isActive;
}
