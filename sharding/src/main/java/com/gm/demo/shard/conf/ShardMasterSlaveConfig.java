package com.gm.demo.shard.conf;

import com.zaxxer.hikari.HikariDataSource;
import io.shardingjdbc.core.api.config.MasterSlaveRuleConfiguration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jason
 */
@Data
@ConfigurationProperties(prefix = "shard.jdbc")
public class ShardMasterSlaveConfig {

    private Map<String, HikariDataSource> dataSources = new HashMap();

    private MasterSlaveRuleConfiguration masterSlaveRule;
}
