package cn.gm.demo.css.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 用户中心启动类.
 *
 * @author Jas °
 * @date 2021 /9/28 (周二)
 */
@SpringBootApplication
@ComponentScan(basePackages = {"cn.gm.demo.css.log", "cn.hll.css.kit", "cn.hll.tools"})
public class LogApp {
    public static void main(String[] args) {
        SpringApplication.run(LogApp.class, args);
    }
}
