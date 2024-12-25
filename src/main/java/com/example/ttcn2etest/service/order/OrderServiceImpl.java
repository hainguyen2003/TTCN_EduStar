package com.example.ttcn2etest.service.order;

import com.example.ttcn2etest.constant.Constant;
import com.example.ttcn2etest.controller.OrderController;
import com.example.ttcn2etest.exception.MyCustomException;
import com.example.ttcn2etest.model.dto.OrderDto;
import com.example.ttcn2etest.model.dto.PaymentRequestDTO;
import com.example.ttcn2etest.model.etity.Order;
import com.example.ttcn2etest.repository.order.OrderRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final String uploadDir = "uploads/";

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Override
    public List<OrderDto> getAllOrder() {
        return orderRepository.findAll().stream().map(
                order -> modelMapper.map(order, OrderDto.class)
        ).toList();
    }

    @Override
    public OrderDto getOrderById(long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()){
            return modelMapper.map(order.get(), OrderDto.class);
        }else {
            throw new MyCustomException("ID của dịch vụ không tồn tại trong hệ thống!");
        }
    }

    @Override
    public OrderDto addOrder(OrderDto orderDto, String paymentCode, String imagePath) {
        String paymentMethod;
        String finalImagePath;

        // Xác định phương thức thanh toán
        if ("DIRECT_PAYMENT".equals(paymentCode)) {
            paymentMethod = "DIRECT_PAYMENT";
            finalImagePath = "Không có"; // Nếu là thanh toán trực tiếp, không có hình ảnh
        } else if ("ONLINE_PAYMENT".equals(paymentCode)) {
            paymentMethod = "ONLINE_PAYMENT";
            finalImagePath = (imagePath != null) ? imagePath : "Không có hình ảnh"; // Nếu không có ảnh, đặt giá trị mặc định
        } else {
            throw new IllegalArgumentException("Invalid payment method code: " + paymentCode);
        }

        // Tạo đối tượng Order từ DTO
        Order order = Order.builder()
                .user(orderDto.getUser())
                .amount(orderDto.getAmount())
                .orderId(generateOrderId())
                .serviceManager(orderDto.getServiceManager())
                .paymentMethod(paymentMethod)
                .status("PENDING")
                .paymentDate(Instant.now())
                .image(finalImagePath) // Đường dẫn hình ảnh
                .email(orderDto.getEmail())
                .fullName(orderDto.getFullName())
                .phone(orderDto.getPhone())
                .address(orderDto.getAddress())
                .build();

        // Lưu vào cơ sở dữ liệu
        order = orderRepository.save(order);

        // Chuyển đổi sang DTO để trả về
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public String saveImage(MultipartFile file, String fileName) throws IOException {
        // Kiểm tra MIME type của file
        String mimeType = Files.probeContentType(Paths.get(file.getOriginalFilename()));
        if (!mimeType.startsWith("image/")) {
            throw new IllegalArgumentException("Tệp tải lên không phải là hình ảnh hợp lệ.");
        }

        String uploadDir = "src/main/resources/static";
        File directory = new File(uploadDir);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        Path filePath = Paths.get(uploadDir, fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return "/" + fileName; // Trả về đường dẫn tương đối để lưu vào cơ sở dữ liệu
    }



    @Override
    public OrderDto updateOrderStatus(OrderDto orderDto, Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isEmpty()) {
            throw new RuntimeException("Order not found with ID: " + orderId);
        }
        Order order = orderOptional.get();

        return null;
    }

    private String generateOrderId() {
        return "ORDER-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

}
