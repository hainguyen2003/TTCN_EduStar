package com.example.ttcn2etest.repository.order;

import com.example.ttcn2etest.model.etity.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
}