package com.example.ttcn2etest.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.ttcn2etest.model.etity.PaymentOnline}
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentOnlineDto {
    Integer id;
    String namePayment;
    Integer stk;
    String bank;
    String image;
}