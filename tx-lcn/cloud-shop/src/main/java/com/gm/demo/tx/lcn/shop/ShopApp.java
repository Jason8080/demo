package com.gm.demo.tx.lcn.shop;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
/**
 * 开启服务注册
 * @author Jason
 */
@EnableEurekaClient
/**
 * 开启Feign远程接口调用
 * @author Jason
 */
@EnableFeignClients(basePackages = "com.gm.demo.tx.lcn.api.service")
@ComponentScan("com.gm.demo.tx.lcn")
@MapperScan("com.gm.demo.tx.lcn.shop.dao")
public class ShopApp {

    public static void main(String[] args) {
        SpringApplication.run(ShopApp.class,args);
    }
}
