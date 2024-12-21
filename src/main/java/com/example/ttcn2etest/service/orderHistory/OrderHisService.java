package com.example.ttcn2etest.service.orderHistory;

import com.example.ttcn2etest.model.dto.OrderHistoryDto;

import java.util.List;

public interface OrderHisService {
    List<OrderHistoryDto> getAllOrderH();

    OrderHistoryDto getOrderHById(long id);

    OrderHistoryDto addOrderH(OrderHistoryDto orderHistoryDto);
}
