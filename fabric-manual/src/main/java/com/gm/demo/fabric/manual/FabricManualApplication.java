package com.gm.demo.fabric.manual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Jason
 */
@SpringBootApplication
@EnableConfigurationProperties
public class FabricManualApplication {

    public static void main(String[] args) {
        SpringApplication.run(FabricManualApplication.class, args);
    }

}
