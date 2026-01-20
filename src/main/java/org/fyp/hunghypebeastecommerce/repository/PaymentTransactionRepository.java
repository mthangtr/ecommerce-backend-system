package org.fyp.hunghypebeastecommerce.repository;

import org.fyp.hunghypebeastecommerce.entity.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long> {
    
    Optional<PaymentTransaction> findByTransactionId(String transactionId);
    
    Optional<PaymentTransaction> findByOrder_Id(Long orderId);
    
    List<PaymentTransaction> findByStatus(String status);
    
    List<PaymentTransaction> findByPaymentMethod(String paymentMethod);
}
