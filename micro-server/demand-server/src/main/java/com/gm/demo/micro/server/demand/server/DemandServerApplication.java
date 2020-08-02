package com.gm.demo.micro.server.demand.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Jason
 */
@EnableEurekaClient
@SpringBootApplication
public class DemandServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemandServerApplication.class, args);
    }
}
