package com.atguigu.springcloud.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentFallbackService  implements  PaymentHystrixService{

    @Override
    public String paymentInfo_Ok(Integer id) {
        return "--------PaymentFallbackService fall back-patmentInfo_OK,/(ㄒoㄒ)/~~";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "--------PaymentFallbackService fall paymentInfo_TimeOut,/(ㄒoㄒ)/~~";
    }
}
