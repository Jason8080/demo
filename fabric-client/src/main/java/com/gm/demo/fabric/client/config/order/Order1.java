package com.gm.demo.fabric.client.config.order;

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
@ConfigurationProperties(prefix = "fabric.order1")
@PropertySource("classpath:fabric-order.properties")
public class Order1 extends Order {
}