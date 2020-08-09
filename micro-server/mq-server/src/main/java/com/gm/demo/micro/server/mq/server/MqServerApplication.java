package com.gm.demo.micro.server.mq.server;

import com.gm.demo.micro.server.mq.server.stream.OneChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Jason
 */
@EnableBinding({OneChannel.class})
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.gm.demo.micro.server.api"})
@ComponentScan(basePackages = {"com.gm.demo.micro.server"})
public class MqServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(MqServerApplication.class, args);
    }
}
