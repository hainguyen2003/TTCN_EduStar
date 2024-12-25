package com.example.ttcn2etest.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.ttcn2etest.model.dto.OrderDto;
import com.example.ttcn2etest.service.order.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.MultipartConfig;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:3000")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 20 // 20MB
)
public class OrderController extends BaseController {
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);


    @GetMapping("/all")
    ResponseEntity<?> getAllOrders() {
        try {
            List<OrderDto> response = orderService.getAllOrder();
            return buildListItemResponse(response, response.size());
        }catch (Exception ex){
            return buildResponse();
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getById(@PathVariable Long id) {
        OrderDto response = orderService.getOrderById(id);
        return buildItemResponse(response);
    }

    @PostMapping("/direct-payment")
    public ResponseEntity<?> directPayment(@Validated @RequestBody OrderDto orderDto) {
        try {
            // Đường dẫn hình ảnh mặc định cho DIRECT_PAYMENT
            String defaultImagePath = "Không có";
            System.out.println(orderDto);

            // Lưu thông tin đơn hàng vào database với paymentCode = "1" (DIRECT_PAYMENT)
            OrderDto response = orderService.addOrder(orderDto, "DIRECT_PAYMENT", defaultImagePath);

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "message", ex.getMessage(),
                    "statusCode", HttpStatus.BAD_REQUEST.value(),
                    "timestamp", Instant.now()
            ));
        }
    }


    @PostMapping("/online-payment")
    public ResponseEntity<?> onlinePayment(
            @RequestParam("order") @Validated OrderDto orderDto,
            @RequestParam("file") MultipartFile file) {
        try {
            // Tạo tên file
            String fileName = "payment_" + orderDto.getId() + "_" + Instant.now().getEpochSecond() + ".jpg";

            // Lưu hình ảnh
            String imagePath = orderService.saveImage(file, fileName);

            // Lưu thông tin đơn hàng vào database với paymentCode = "ONLINE_PAYMENT"
            OrderDto response = orderService.addOrder(orderDto, "ONLINE_PAYMENT", imagePath);

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "message", ex.getMessage(),
                    "statusCode", HttpStatus.BAD_REQUEST.value(),
                    "timestamp", Instant.now()
            ));
        }
    }
}
