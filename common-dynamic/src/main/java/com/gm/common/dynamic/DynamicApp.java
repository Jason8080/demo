package com.gm.common.dynamic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 *
 * @author Timi.lee
 * @date 2020/11/5 (周四)
 */
@SpringBootApplication
@ComponentScan({"com.gm.common.dynamic","cn.huolala.common"})
public class DynamicApp {
    public static void main(String[] args) {
        SpringApplication.run(DynamicApp.class, args);
    }
}
