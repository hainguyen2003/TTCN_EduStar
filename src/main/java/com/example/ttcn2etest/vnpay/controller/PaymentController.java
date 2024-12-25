//package com.example.ttcn2etest.vnpay.controller;
//
//import com.example.ttcn2etest.vnpay.dto.PaymentDTO;
//import com.example.ttcn2etest.vnpay.response.ResponseObject;
//import com.example.ttcn2etest.vnpay.service.PaymentService;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/paymentVNPAY")
//public class PaymentController {
//    private final PaymentService paymentService;
//
//    public PaymentController(PaymentService paymentService) {
//        this.paymentService = paymentService;
//    }
//
//    @GetMapping("/vn-pay")
//    public ResponseObject<PaymentDTO.VNPayResponse> pay(HttpServletRequest request) {
//        return new ResponseObject<>(HttpStatus.OK, "Success", paymentService.createVnPayPayment(request));
//    }
//    @GetMapping("/vn-pay-callback")
//    public ResponseObject<PaymentDTO.VNPayResponse> payCallbackHandler(HttpServletRequest request) {
//        String status = request.getParameter("vnp_ResponseCode");
//        if (status.equals("00")) {
//            return new ResponseObject<>(HttpStatus.OK, "Success", new PaymentDTO.VNPayResponse("00", "Success", ""));
//        } else {
//            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", null);
//        }
//    }
//}
