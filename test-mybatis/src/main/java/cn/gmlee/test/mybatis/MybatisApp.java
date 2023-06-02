package cn.gmlee.test.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * .
 *
 * @author Jas°
 * @date 2021/8/27 (周五)
 */
@SpringBootApplication
@ComponentScan({"cn.gmlee.test", "cn.gmlee.tools"})
public class MybatisApp {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApp.class, args);
    }
}
