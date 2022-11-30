package com.atguigu.springcloud.service.Impl;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String paymentInfo_Ok(Integer id) {
        return "线程池:"+Thread.currentThread().getName()+"paymentInfo_Ok,id："+id;
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id) {
//        int timeNumber = 5;
//        try{
//            TimeUnit.SECONDS.sleep(timeNumber);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        int count = 10 / 0;
        return "线程池:"+Thread.currentThread().getName()+"paymentInfo_TimeOut,id："+id;
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池:"+Thread.currentThread().getName()+"系统繁忙、运行出错请稍后再试/(ㄒoㄒ)/~~";
    }



    //======服务熔断

    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //是否开启熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //执行次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), //执行时间
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //达到60%就进行熔断
    })
    public String paymentCircuitBreaker(Integer id) {
        if(id < 0){
            throw new RuntimeException("******id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"调用成功,流水号:"+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(Integer id){
        return "Id 不能为负数，请稍后再试。";
    }
}
