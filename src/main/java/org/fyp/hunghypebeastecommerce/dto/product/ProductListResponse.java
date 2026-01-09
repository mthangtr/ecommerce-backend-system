package org.fyp.hunghypebeastecommerce.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductListResponse {
    private List<ProductListItemDTO> products;
    private Integer currentPage;
    private Integer totalPages;
    private Long totalItems;
    private Integer pageSize;
}
