package com.gm.demo.shard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Jason
 */
@SpringBootApplication
@EnableSwagger2
public class ShardApp {
    public static void main(String[] args) {
        SpringApplication.run(ShardApp.class, args);
    }
}
