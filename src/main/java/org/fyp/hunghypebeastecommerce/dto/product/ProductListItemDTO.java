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
public class ProductListItemDTO {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String category;
    private Integer totalStock;
}
