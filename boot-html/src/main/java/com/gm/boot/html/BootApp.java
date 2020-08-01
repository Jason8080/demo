package com.gm.boot.html;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Jason
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class BootApp {

    public static void main(String[] args) {
        SpringApplication.run(BootApp.class, args);
    }
}
