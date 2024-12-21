package com.example.ttcn2etest.repository.order;

import com.example.ttcn2etest.model.etity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}