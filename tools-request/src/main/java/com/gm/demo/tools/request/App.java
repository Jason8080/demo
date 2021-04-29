package com.gm.demo.tools.request;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Jas°
 * @date 2021/4
 * /25 (周日)
 */
@SpringBootApplication(exclude = {
//        ReplaceStreamFilterAutoConfiguration.class,
})
@ComponentScan({"cn.gmlee", "com.gm.demo"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
