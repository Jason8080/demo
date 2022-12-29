package com.gm.demo.tools.sharding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Jas°
 * @date 2021/6/10 (周四)
 */
@SpringBootApplication
@ComponentScan({"com.gm.demo", "cn.hll.tools"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
