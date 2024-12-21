package com.example.ttcn2etest.service.orderHistory;

import com.example.ttcn2etest.exception.MyCustomException;
import com.example.ttcn2etest.model.dto.OrderHistoryDto;
import com.example.ttcn2etest.model.etity.OrderHistory;
import com.example.ttcn2etest.repository.order.OrderHistoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class OrderHisServiceImpl implements OrderHisService{
    private final OrderHistoryRepository orderHistoryRepository;
    private final ModelMapper modelMapper;

    public OrderHisServiceImpl(OrderHistoryRepository orderHistoryRepository, ModelMapper modelMapper) {
        this.orderHistoryRepository = orderHistoryRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<OrderHistoryDto> getAllOrderH() {
        return orderHistoryRepository.findAll().stream().map(
                orderH -> modelMapper.map(orderH, OrderHistoryDto.class)
        ).toList();
    }

    @Override
    public OrderHistoryDto getOrderHById(long id) {
        Optional<OrderHistory> orderHistory = orderHistoryRepository.findById(id);
        if (orderHistory.isPresent()){
            return modelMapper.map(orderHistory.get(), OrderHistoryDto.class);
        }else {
            throw new MyCustomException("Đơn hàng của bạn không tồn tại !");
        }
    }

    @Override
    public OrderHistoryDto addOrderH(OrderHistoryDto orderHistoryDto) {
        try {
            OrderHistory orderHistory = OrderHistory.builder()
                    .id(orderHistoryDto.getId())
                    .order(orderHistoryDto.getOrder())
                    .amount(orderHistoryDto.getAmount())
                    .transactionDate(Instant.now())
                    .status(orderHistoryDto.getStatus())
                    .notes(orderHistoryDto.getNotes())
                    .build();
            orderHistory = orderHistoryRepository.saveAndFlush(orderHistory);
            return modelMapper.map(orderHistory, OrderHistoryDto.class);
        }catch (Exception e){
            throw new MyCustomException("Có lỗi xảy ra trong quá trình thanh toán");
        }
    }
}
