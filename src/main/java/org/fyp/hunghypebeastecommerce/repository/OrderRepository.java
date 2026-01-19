package org.fyp.hunghypebeastecommerce.repository;

import org.fyp.hunghypebeastecommerce.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByOrderNumber(String orderNumber);
    Optional<Order> findByTrackingToken(UUID trackingToken);
    Page<Order> findByCustomerEmail(String customerEmail, Pageable pageable);
    Page<Order> findByStatus(String status, Pageable pageable);
    Page<Order> findAll(Pageable pageable);
}
