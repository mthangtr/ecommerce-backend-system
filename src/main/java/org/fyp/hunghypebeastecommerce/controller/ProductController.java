package org.fyp.hunghypebeastecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.fyp.hunghypebeastecommerce.dto.ResponseObject;
import org.fyp.hunghypebeastecommerce.dto.product.ProductDTO;
import org.fyp.hunghypebeastecommerce.dto.product.ProductFilterRequest;
import org.fyp.hunghypebeastecommerce.dto.product.ProductListResponse;
import org.fyp.hunghypebeastecommerce.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ResponseObject<ProductListResponse>> getProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection
    ) {
        ProductFilterRequest request = ProductFilterRequest.builder()
                .category(category)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .sortDirection(sortDirection)
                .build();

        ProductListResponse response = productService.getProducts(request);
        return ResponseEntity.ok(ResponseObject.success("Products retrieved successfully", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject<ProductDTO>> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(ResponseObject.success("Product retrieved successfully", product));
    }
}
