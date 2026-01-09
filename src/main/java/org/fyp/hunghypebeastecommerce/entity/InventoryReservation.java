package org.fyp.hunghypebeastecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_reservations", indexes = {
    @Index(name = "idx_reservations_variant", columnList = "variant_id"),
    @Index(name = "idx_reservations_session", columnList = "session_id"),
    @Index(name = "idx_reservations_expires", columnList = "expires_at, status"),
    @Index(name = "idx_reservations_order", columnList = "reserved_for_order_id")
})
@Getter
@Setter
public class InventoryReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id", nullable = false)
    private ProductVariant variant;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "reserved_for_order_id")
    private Long reservedForOrderId;

    @Column(name = "session_id", nullable = false)
    private String sessionId;

    @Column(name = "reserved_at", updatable = false)
    private LocalDateTime reservedAt;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Column(length = 20)
    private String status = "active"; // 'active', 'completed', 'expired', 'cancelled'

    @PrePersist
    protected void onCreate() {
        reservedAt = LocalDateTime.now();
    }
}
