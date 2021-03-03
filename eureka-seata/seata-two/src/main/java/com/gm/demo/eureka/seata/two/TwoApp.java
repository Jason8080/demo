package com.gm.demo.eureka.seata.two;

import cn.gmlee.tools.redis.config.RedisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Jas°
 * @date 2021/2/24 (周三)
 */
@EnableDiscoveryClient
@EnableFeignClients({"com.gm.demo.eureka.seata"})
@SpringBootApplication(exclude = {RedisAutoConfiguration.class, org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration.class})
@ComponentScan(value = {"cn.gmlee.tools", "com.gm.demo.eureka.seata.two"})
public class TwoApp {
    public static void main(String[] args) {
        SpringApplication.run(TwoApp.class, args);
    }
}
