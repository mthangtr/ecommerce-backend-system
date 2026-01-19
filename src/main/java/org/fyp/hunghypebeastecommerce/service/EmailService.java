package org.fyp.hunghypebeastecommerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fyp.hunghypebeastecommerce.entity.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${app.tracking.base-url:http://localhost:3000}")
    private String trackingBaseUrl;

    public void sendOrderConfirmation(Order order) {
        try {
            String trackingLink = buildTrackingLink(order.getTrackingToken());
            String subject = "Order Confirmation - " + order.getOrderNumber();
            String body = buildOrderConfirmationEmail(order, trackingLink);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(order.getCustomerEmail());
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);
            log.info("Order confirmation email sent to: {}", order.getCustomerEmail());
        } catch (Exception e) {
            log.error("Failed to send order confirmation email for order: {}", order.getOrderNumber(), e);
        }
    }

    private String buildOrderConfirmationEmail(Order order, String trackingLink) {
        return "Thank you for your order!\n\n" +
                "Order Number: " + order.getOrderNumber() + "\n" +
                "Order Date: " + order.getCreatedAt() + "\n" +
                "Total Amount: " + order.getTotalAmount() + " VND\n\n" +
                "Shipping Address:\n" +
                order.getShippingAddress() + "\n" +
                order.getShippingCity() + ", " + order.getShippingDistrict() + "\n\n" +
                "Track your order: " + trackingLink + "\n\n" +
                "Thank you for shopping with us!";
    }

    private String buildTrackingLink(UUID trackingToken) {
        return trackingBaseUrl + "/track/" + trackingToken;
    }
}
