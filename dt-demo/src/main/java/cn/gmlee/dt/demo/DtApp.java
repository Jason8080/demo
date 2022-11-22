package cn.gmlee.dt.demo;

import cn.gmlee.dt.spring.boot.starter.conf.DtSpringTransactionAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The type Main app.
 */
@SpringBootApplication(exclude = {
        DtSpringTransactionAutoConfiguration.class,
})
@ComponentScan({"cn.gmlee.dt", "cn.hll.tools"})
public class DtApp {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(DtApp.class, args);
    }
}
