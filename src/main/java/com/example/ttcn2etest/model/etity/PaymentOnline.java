package com.example.ttcn2etest.model.etity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "payment_online")
public class PaymentOnline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 250)
    @NotNull
    @Column(name = "name_payment", nullable = false, length = 250)
    private String namePayment;

    @NotNull
    @Column(name = "stk", nullable = false)
    private Integer stk;

    @Size(max = 250)
    @NotNull
    @Column(name = "bank", nullable = false, length = 250)
    private String bank;

    @Size(max = 250)
    @NotNull
    @Column(name = "image", nullable = false, length = 250)
    private String image;

}