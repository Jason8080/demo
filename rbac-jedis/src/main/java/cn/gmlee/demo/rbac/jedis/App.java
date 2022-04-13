package cn.gmlee.demo.rbac.jedis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * .
 *
 * @author Jas°
 * @date 2021/9/18 (周六)
 */
@SpringBootApplication
@ComponentScan({"cn.hll.css.kit", "cn.gmlee.demo.rbac.jedis"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
