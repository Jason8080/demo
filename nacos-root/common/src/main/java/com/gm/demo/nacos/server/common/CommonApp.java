package com.gm.demo.nacos.server.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * 测试通用工具
 *
 * @author Timi.lee
 * @date 2020/8/28 (周五)
 */
@SpringBootApplication
@PropertySource("classpath:common.properties")
public class CommonApp {

    public static void main(String[] args) {
        SpringApplication.run(CommonApp.class, args);
    }

}
