package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {



    @Bean

    /**
     * @LoadBalanced
     * 注意：如果一个服务做了集群之后如果不加这个注解可能会找不到哪一个服务给我运行所以必须加这个注解让我的服务具有负载均衡的能力，这样就不会报错了
     * 这里要声明一下默认的负载均衡的机制是轮询就是一个服务一次轮着来
     * 首先：这个@LoadBalanced注解是来自cloud包下的一个注解
     * 这个注解就是让某一个东西拥有负载均衡的能力
     *
     * 这里就是让这个RestTemplate在请求时拥有客户端负载均衡的能力 ：RestTemplate 这个可以理解成为客服端
     */
//    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
