package com.example.ttcn2etest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentRequestDTO {
    private String orderId;
    private String amount;
    private String paymentMethod;
    private String transferImage; // Base64 hình ảnh chuyển khoản
    private String userEmail;
    private String userName;
    private String userPhone;
    private String userAddress;
}

