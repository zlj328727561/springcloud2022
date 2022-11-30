package com.atguigu.springcloud.service;

import org.springframework.web.bind.annotation.PathVariable;

public interface PaymentService {

    String paymentInfo_Ok(Integer id);
    String paymentInfo_TimeOut(Integer id);
    String paymentCircuitBreaker(Integer id);
}
