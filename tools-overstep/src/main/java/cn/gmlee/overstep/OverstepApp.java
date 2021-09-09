package cn.gmlee.overstep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * .
 *
 * @author Jas°
 * @date 2021/7/28 (周三)
 */
@SpringBootApplication
@ComponentScan("cn.gmlee")
@EnableFeignClients("cn.gmlee.overstep.api")
public class OverstepApp {

    public static void main(String[] args) {
        SpringApplication.run(OverstepApp.class, args);
    }
}
