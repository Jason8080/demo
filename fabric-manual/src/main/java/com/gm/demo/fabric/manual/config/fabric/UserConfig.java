package com.gm.demo.fabric.manual.config.fabric;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Jason
 */
@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "fabric.user")
@PropertySource("classpath:fabric-user.properties")
public class UserConfig {
    private String adminName;
    private String adminPass;
    private String newName;
}
