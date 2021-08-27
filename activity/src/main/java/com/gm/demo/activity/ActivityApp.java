package com.gm.demo.activity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * .
 *
 * @author Jas°
 * @date 2021/8/5 (周四)
 */
@SpringBootApplication
@ComponentScan({"cn.gmlee.tools", "com.gm.demo"})
public class ActivityApp {

    public static void main(String[] args) {
        SpringApplication.run(ActivityApp.class, args);
    }
}
