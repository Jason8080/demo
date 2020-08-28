package com.gm.demo.nacos.server.provider;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.gm.demo.nacos.server.api.HelloApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.gm.demo.nacos.server.api"})
@ComponentScan("com.gm.demo.nacos.server")
@PropertySource({"classpath:application.properties", "classpath:common.properties"})
public class NacosConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApp.class, args);
    }

    @Autowired
    private HelloApi helloApi;

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @GetMapping("test")
    @SentinelResource("test")
    public Object test() {
        return helloApi.hello();
    }
}
