package com.gm.demo.shard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Jason
 */
@SpringBootApplication
@MapperScan("com.gm.demo.shard.dao.mapper")
@EnableSwagger2
public class ShardApp {
    public static void main(String[] args) {
        SpringApplication.run(ShardApp.class, args);
    }
}
