package org.fyp.hunghypebeastecommerce.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.fyp.hunghypebeastecommerce.dto.ResponseObject;
import org.fyp.hunghypebeastecommerce.dto.admin.OrderListItemDTO;
import org.fyp.hunghypebeastecommerce.dto.admin.UpdateOrderStatusRequest;
import org.fyp.hunghypebeastecommerce.dto.checkout.OrderDTO;
import org.fyp.hunghypebeastecommerce.dto.checkout.OrderItemDTO;
import org.fyp.hunghypebeastecommerce.entity.Order;
import org.fyp.hunghypebeastecommerce.entity.OrderItem;
import org.fyp.hunghypebeastecommerce.exception.CustomException;
import org.fyp.hunghypebeastecommerce.exception.ErrorCode;
import org.fyp.hunghypebeastecommerce.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<ResponseObject<?>> listOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String status,
            HttpSession session
    ) {
        Long adminId = (Long) session.getAttribute("adminId");
        if (adminId == null) {
            throw new CustomException(ErrorCode.INVALID_INPUT);
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orders = orderService.listOrders(status, pageable);

        var result = orders.stream()
                .map(this::mapToOrderListItem)
                .collect(Collectors.toList());

        return ResponseEntity.ok(ResponseObject.success("Orders retrieved successfully", 
                new PageResponse<>(result, orders.getTotalPages(), orders.getTotalElements())));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseObject<OrderDTO>> getOrder(
            @PathVariable Long orderId,
            HttpSession session
    ) {
        Long adminId = (Long) session.getAttribute("adminId");
        if (adminId == null) {
            throw new CustomException(ErrorCode.INVALID_INPUT);
        }

        Order order = orderService.getOrderById(orderId);
        OrderDTO orderDTO = mapToOrderDTO(order);
        return ResponseEntity.ok(ResponseObject.success("Order retrieved successfully", orderDTO));
    }

    @PatchMapping("/{orderId}/status")
    public ResponseEntity<ResponseObject<OrderDTO>> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestBody UpdateOrderStatusRequest request,
            HttpSession session
    ) {
        Long adminId = (Long) session.getAttribute("adminId");
        if (adminId == null) {
            throw new CustomException(ErrorCode.INVALID_INPUT);
        }

        String changedBy = "admin:" + adminId;
        Order updatedOrder = orderService.updateOrderStatus(orderId, request.getStatus(), request.getAdminNote(), changedBy);
        OrderDTO orderDTO = mapToOrderDTO(updatedOrder);
        return ResponseEntity.ok(ResponseObject.success("Order status updated successfully", orderDTO));
    }

    private OrderListItemDTO mapToOrderListItem(Order order) {
        return OrderListItemDTO.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .customerName(order.getCustomerName())
                .customerEmail(order.getCustomerEmail())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .paymentStatus(order.getPaymentStatus())
                .createdAt(order.getCreatedAt())
                .build();
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

    public static class PageResponse<T> {
        public java.util.List<T> content;
        public int totalPages;
        public long totalElements;

        public PageResponse(java.util.List<T> content, int totalPages, long totalElements) {
            this.content = content;
            this.totalPages = totalPages;
            this.totalElements = totalElements;
        }
    }
}
