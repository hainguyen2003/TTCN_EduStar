package com.example.ttcn2etest.repository.paymentOnline;

import com.example.ttcn2etest.model.etity.PaymentOnline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentOnlineRepository extends JpaRepository<PaymentOnline, Integer> {
}