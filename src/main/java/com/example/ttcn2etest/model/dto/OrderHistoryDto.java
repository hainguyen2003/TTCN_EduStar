package com.example.ttcn2etest.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link com.example.ttcn2etest.model.etity.OrderHistory}
 */
@Value
public class OrderHistoryDto implements Serializable {
    Long id;
    @NotNull
    OrderDto order;
    @NotNull
    BigDecimal amount;
    Instant transactionDate;
    @NotNull
    String status;
    String notes;
}