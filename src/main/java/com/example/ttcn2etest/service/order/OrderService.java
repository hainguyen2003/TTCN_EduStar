package com.example.ttcn2etest.service.order;

import com.example.ttcn2etest.model.dto.OrderDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrder();

    OrderDto getOrderById(long orderId);


    // add Order
    OrderDto addOrder(OrderDto orderDto, String paymentCode, String imagePath);


    //
    String saveImage(MultipartFile file, String fileName) throws IOException;


    // update Order
    OrderDto updateOrderStatus(OrderDto orderDto,Long orderId);
}
