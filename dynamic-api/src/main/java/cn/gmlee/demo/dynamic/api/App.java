package cn.gmlee.demo.dynamic.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"cn.gmlee"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
