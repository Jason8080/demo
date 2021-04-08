package com.gm.data.util;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The type Start app.
 *
 * @author Jas °
 * @date 2021 /3/29 (周一)
 */
@SpringBootApplication
@ComponentScan({"com.gm", "cn.gmlee"})
public class StartApp {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(StartApp.class, args);
    }
}
