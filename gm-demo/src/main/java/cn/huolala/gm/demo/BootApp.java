package cn.huolala.gm.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Timi.lee
 * @date 2021/1/12 (周二)
 */
@SpringBootApplication
@ComponentScan({"cn.huolala.gm.demo", "cn.huolala.common"})
public class BootApp {

    public static void main(String[] args) {
        SpringApplication.run(BootApp.class, args);
    }
}
