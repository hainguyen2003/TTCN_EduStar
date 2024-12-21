package com.example.ttcn2etest.model.etity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
//@Table(name = "order_history", schema = "edustar")
@Table(name = "order_history")

public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @NotNull
    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "transaction_date")
    private Instant transactionDate;

    @NotNull
    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @Lob
    @Column(name = "notes")
    private String notes;

}