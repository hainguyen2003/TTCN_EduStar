package com.example.ttcn2etest.service.paymentOnline;

import com.example.ttcn2etest.exception.MyCustomException;
import com.example.ttcn2etest.model.dto.PaymentOnlineDto;
import com.example.ttcn2etest.model.etity.PaymentOnline;
import com.example.ttcn2etest.repository.paymentOnline.PaymentOnlineRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentOnlineServiceImpl implements PaymentOnlineService {
    private final PaymentOnlineRepository paymentOnlineRepository;
    private final ModelMapper modelMapper;

    public PaymentOnlineServiceImpl(PaymentOnlineRepository paymentOnlineRepository, ModelMapper modelMapper) {
        this.paymentOnlineRepository = paymentOnlineRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PaymentOnlineDto getByIdPaymentOnline(int id) {
        Optional<PaymentOnline> paymentOnline = paymentOnlineRepository.findById(id);
        if(paymentOnline.isPresent()) {
            return modelMapper.map(paymentOnline.get(), PaymentOnlineDto.class);
        }else{
            throw new MyCustomException("ID của dịch vụ không tồn tại trong hệ thống!");
        }
    }


    @Override
    public PaymentOnlineDto updatePaymentOnline(PaymentOnlineDto paymentOnlineDto, int idPaymentOnline) {
        Optional<PaymentOnline> paymentOnline = paymentOnlineRepository.findById(idPaymentOnline);
        if (paymentOnline.isPresent()) {
            PaymentOnline paymentOnlineEntity = paymentOnline.get();
            paymentOnlineEntity.setNamePayment(paymentOnlineDto.getNamePayment());
            paymentOnlineEntity.setBank(paymentOnlineDto.getBank());
            paymentOnlineEntity.setStk(paymentOnlineDto.getStk());
            paymentOnlineEntity.setImage(paymentOnlineDto.getImage());
            return modelMapper.map(paymentOnlineRepository.save(paymentOnlineEntity), PaymentOnlineDto.class);
        }

        throw new MyCustomException("Có lỗi xảy ra trong quá trình cập nhật dịch vụ!");
    }
}
