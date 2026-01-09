package org.fyp.hunghypebeastecommerce.repository;

import org.fyp.hunghypebeastecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT DISTINCT p FROM Product p " +
            "LEFT JOIN p.variants v " +
            "WHERE p.isActive = true " +
            "AND (:category IS NULL OR p.category = :category) " +
            "AND (:minPrice IS NULL OR (p.basePrice + COALESCE(v.priceAdjustment, 0)) >= :minPrice) " +
            "AND (:maxPrice IS NULL OR (p.basePrice + COALESCE(v.priceAdjustment, 0)) <= :maxPrice)")
    Page<Product> findByFilters(
            @Param("category") String category,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            Pageable pageable
    );
}
