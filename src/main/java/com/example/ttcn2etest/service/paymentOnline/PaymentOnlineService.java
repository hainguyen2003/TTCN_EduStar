package com.example.ttcn2etest.service.paymentOnline;

import com.example.ttcn2etest.model.dto.PaymentOnlineDto;

public interface PaymentOnlineService {
    PaymentOnlineDto getByIdPaymentOnline(int idPaymentOnline);

    PaymentOnlineDto updatePaymentOnline(PaymentOnlineDto paymentOnlineDto, int idPaymentOnline);

}
