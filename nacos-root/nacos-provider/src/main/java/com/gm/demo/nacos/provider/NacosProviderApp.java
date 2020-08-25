package com.gm.demo.nacos.provider;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableDiscoveryClient
public class NacosProviderApp {
    public static void main(String[] args) {
        SpringApplication.run(NacosProviderApp.class, args);
    }


    @GetMapping("hello")
    @SentinelResource("hello")
    public String hello(){
        System.out.println("计数器+1");
        return "Hello，nacos！";
    }
}
