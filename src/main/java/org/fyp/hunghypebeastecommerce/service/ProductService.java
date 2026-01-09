package org.fyp.hunghypebeastecommerce.service;

import lombok.RequiredArgsConstructor;
import org.fyp.hunghypebeastecommerce.dto.product.*;
import org.fyp.hunghypebeastecommerce.entity.Product;
import org.fyp.hunghypebeastecommerce.entity.ProductVariant;
import org.fyp.hunghypebeastecommerce.exception.CustomException;
import org.fyp.hunghypebeastecommerce.exception.ErrorCode;
import org.fyp.hunghypebeastecommerce.repository.ProductRepository;
import org.fyp.hunghypebeastecommerce.repository.ProductVariantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public ProductListResponse getProducts(ProductFilterRequest request) {
        validateFilterRequest(request);

        Pageable pageable = createPageable(request);
        
        Page<Product> productPage = productRepository.findByFilters(
                request.getCategory(),
                request.getMinPrice(),
                request.getMaxPrice(),
                pageable
        );

        List<ProductListItemDTO> productDTOs = productPage.getContent().stream()
                .map(this::convertToListItemDTO)
                .collect(Collectors.toList());

        return ProductListResponse.builder()
                .products(productDTOs)
                .currentPage(productPage.getNumber())
                .totalPages(productPage.getTotalPages())
                .totalItems(productPage.getTotalElements())
                .pageSize(productPage.getSize())
                .build();
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .filter(Product::getIsActive)
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));

        return convertToDetailDTO(product);
    }

    private void validateFilterRequest(ProductFilterRequest request) {
        if (request.getMinPrice() != null && request.getMaxPrice() != null) {
            if (request.getMinPrice().compareTo(request.getMaxPrice()) > 0) {
                throw new CustomException(ErrorCode.INVALID_PRICE_RANGE);
            }
        }

        if (request.getPage() < 0 || request.getSize() <= 0 || request.getSize() > 100) {
            throw new CustomException(ErrorCode.INVALID_PAGINATION);
        }
    }

    private Pageable createPageable(ProductFilterRequest request) {
        Sort.Direction direction = "DESC".equalsIgnoreCase(request.getSortDirection())
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        String sortField = mapSortField(request.getSortBy());
        Sort sort = Sort.by(direction, sortField);

        return PageRequest.of(request.getPage(), request.getSize(), sort);
    }

    private String mapSortField(String sortBy) {
        return switch (sortBy.toLowerCase()) {
            case "name" -> "name";
            case "price" -> "basePrice";
            case "createdat", "created" -> "createdAt";
            default -> "createdAt";
        };
    }

    private ProductListItemDTO convertToListItemDTO(Product product) {
        List<ProductVariant> variants = product.getVariants();
        
        BigDecimal minPrice = variants.stream()
                .filter(ProductVariant::getIsActive)
                .map(v -> product.getBasePrice().add(v.getPriceAdjustment()))
                .min(BigDecimal::compareTo)
                .orElse(product.getBasePrice());

        BigDecimal maxPrice = variants.stream()
                .filter(ProductVariant::getIsActive)
                .map(v -> product.getBasePrice().add(v.getPriceAdjustment()))
                .max(BigDecimal::compareTo)
                .orElse(product.getBasePrice());

        Integer totalStock = variants.stream()
                .filter(ProductVariant::getIsActive)
                .mapToInt(v -> v.getStockQuantity() - v.getReservedQuantity())
                .sum();

        return ProductListItemDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .slug(product.getSlug())
                .description(product.getDescription())
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .category(product.getCategory())
                .totalStock(totalStock)
                .build();
    }

    private ProductDTO convertToDetailDTO(Product product) {
        List<ProductVariantDTO> variantDTOs = product.getVariants().stream()
                .filter(ProductVariant::getIsActive)
                .map(this::convertToVariantDTO)
                .collect(Collectors.toList());

        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .slug(product.getSlug())
                .description(product.getDescription())
                .basePrice(product.getBasePrice())
                .category(product.getCategory())
                .isActive(product.getIsActive())
                .variants(variantDTOs)
                .build();
    }

    private ProductVariantDTO convertToVariantDTO(ProductVariant variant) {
        BigDecimal finalPrice = variant.getProduct().getBasePrice()
                .add(variant.getPriceAdjustment());
        
        Integer availableStock = variant.getStockQuantity() - variant.getReservedQuantity();

        return ProductVariantDTO.builder()
                .id(variant.getId())
                .sku(variant.getSku())
                .size(variant.getSize())
                .color(variant.getColor())
                .price(finalPrice)
                .availableStock(availableStock)
                .isActive(variant.getIsActive())
                .build();
    }
}
