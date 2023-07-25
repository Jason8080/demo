package cn.gmlee.demo.k8s.app.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The type App.
 */
@SpringBootApplication
@ComponentScan({"cn.gmlee"})
public class App {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
