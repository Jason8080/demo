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
@ConfigurationProperties(prefix = "fabric.per0.org2")
@PropertySource("classpath:fabric-peer.properties")
public class Peer0Org2Config extends TlsConfig {
}
