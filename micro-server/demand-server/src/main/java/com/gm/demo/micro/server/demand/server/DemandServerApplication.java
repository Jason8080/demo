package com.gm.demo.micro.server.demand.server;

import com.gm.demo.micro.server.api.order.GrabOrderApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * @author Jason
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = "com.gm.demo.micro.server")
public class DemandServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemandServerApplication.class, args);
    }
}
