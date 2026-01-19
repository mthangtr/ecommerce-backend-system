package org.fyp.hunghypebeastecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.fyp.hunghypebeastecommerce.dto.ResponseObject;
import org.fyp.hunghypebeastecommerce.dto.checkout.OrderDTO;
import org.fyp.hunghypebeastecommerce.dto.checkout.OrderItemDTO;
import org.fyp.hunghypebeastecommerce.entity.Order;
import org.fyp.hunghypebeastecommerce.entity.OrderItem;
import org.fyp.hunghypebeastecommerce.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderTrackingController {

    private final OrderService orderService;

    @GetMapping("/track/{token}")
    public ResponseEntity<ResponseObject<OrderDTO>> trackOrder(
            @PathVariable UUID token
    ) {
        Order order = orderService.getOrderByTrackingToken(token);
        OrderDTO orderDTO = mapToOrderDTO(order);
        return ResponseEntity.ok(ResponseObject.success("Order retrieved successfully", orderDTO));
    }

    private OrderDTO mapToOrderDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .trackingToken(order.getTrackingToken())
                .customerName(order.getCustomerName())
                .customerEmail(order.getCustomerEmail())
                .customerPhone(order.getCustomerPhone())
                .shippingAddress(order.getShippingAddress())
                .shippingCity(order.getShippingCity())
                .shippingDistrict(order.getShippingDistrict())
                .paymentMethod(order.getPaymentMethod())
                .paymentStatus(order.getPaymentStatus())
                .subtotal(order.getSubtotal())
                .shippingFee(order.getShippingFee())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .customerNote(order.getCustomerNote())
                .adminNote(order.getAdminNote())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .confirmedAt(order.getConfirmedAt())
                .paidAt(order.getPaidAt())
                .shippedAt(order.getShippedAt())
                .completedAt(order.getCompletedAt())
                .cancelledAt(order.getCancelledAt())
                .items(order.getItems().stream()
                        .map(this::mapToOrderItemDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    private OrderItemDTO mapToOrderItemDTO(OrderItem item) {
        return OrderItemDTO.builder()
                .id(item.getId())
                .variantId(item.getVariant().getId())
                .productName(item.getProductName())
                .variantSku(item.getVariantSku())
                .variantSize(item.getVariantSize())
                .variantColor(item.getVariantColor())
                .unitPrice(item.getUnitPrice())
                .quantity(item.getQuantity())
                .subtotal(item.getSubtotal())
                .build();
    }
}
