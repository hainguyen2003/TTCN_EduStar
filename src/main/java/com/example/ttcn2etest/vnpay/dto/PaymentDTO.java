package com.example.ttcn2etest.vnpay.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.N;

public abstract class PaymentDTO {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class VNPayResponse {
        public String code;
        public String message;
        public String paymentUrl;
    }
}
