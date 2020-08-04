package com.gm.demo.micro.server.stock.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Jason
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class StockServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockServerApplication.class, args);
    }
}
