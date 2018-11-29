package com.gm.demo.tx.lcn.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author Jason
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan("com.gm.demo.tx.lcn")
@MapperScan("com.gm.demo.tx.lcn.order.dao")
public class OrderApp {

    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class, args);
    }

}
