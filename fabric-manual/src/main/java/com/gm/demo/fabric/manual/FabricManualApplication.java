package com.gm.demo.fabric.manual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Jason
 */
@EnableSwagger2
@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan("com.gm.demo.fabric.manual")
public class FabricManualApplication {

    public static void main(String[] args) {
        SpringApplication.run(FabricManualApplication.class, args);
    }

}
