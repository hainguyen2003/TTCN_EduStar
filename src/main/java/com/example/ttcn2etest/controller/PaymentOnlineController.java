package com.example.ttcn2etest.controller;

import com.example.ttcn2etest.model.dto.PaymentOnlineDto;
import com.example.ttcn2etest.service.paymentOnline.PaymentOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment-online")
public class PaymentOnlineController extends BaseController {
    private final PaymentOnlineService paymentOnlineService;

    @Autowired
    public PaymentOnlineController(PaymentOnlineService paymentOnlineService) {
        this.paymentOnlineService = paymentOnlineService;
    }


    // Lấy thông tin thanh toán online theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentOnlineById(@PathVariable int id) {
        PaymentOnlineDto response = paymentOnlineService.getByIdPaymentOnline(id);
        return buildItemResponse(response);
    }

    // Cập nhật thông tin thanh toán online theo ID
    @PutMapping("/{id}")
    public ResponseEntity<PaymentOnlineDto> updatePaymentOnline(
            @PathVariable("id") int idPaymentOnline,
            @RequestBody PaymentOnlineDto paymentOnlineDto) {
        PaymentOnlineDto updatedPayment = paymentOnlineService.updatePaymentOnline(paymentOnlineDto, idPaymentOnline);
        if (updatedPayment != null) {
            return ResponseEntity.ok(updatedPayment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
