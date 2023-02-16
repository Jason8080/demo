package cn.gmlee.dt.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The type Main app.
 */
@SpringBootApplication
@ComponentScan({"cn.gmlee.dt", "cn.hll.tools"})
public class DtApp {

    /**
     * The entry point of application.CtClientAutoConfiguration
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(DtApp.class, args);
    }
}
