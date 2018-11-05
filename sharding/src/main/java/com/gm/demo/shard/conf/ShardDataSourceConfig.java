package com.gm.demo.shard.conf;

import com.google.common.collect.Maps;
import io.shardingjdbc.core.api.MasterSlaveDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Jason
 */
@Configuration
@EnableConfigurationProperties(ShardMasterSlaveConfig.class)
@ConditionalOnProperty({
        "shard.jdbc.data-sources.ds_master.jdbc-url",
        "shard.jdbc.master-slave-rule.master-data-source-name"
})
public class ShardDataSourceConfig {

    @Autowired
    private ShardMasterSlaveConfig shardMasterSlaveConfig;

    @Bean
    public DataSource masterSlaveDataSource() throws SQLException {
        Map<String, DataSource> dataSourceMap = Maps.newHashMap();
        dataSourceMap.putAll(shardMasterSlaveConfig.getDataSources());
        DataSource dataSource = MasterSlaveDataSourceFactory.createDataSource(dataSourceMap, shardMasterSlaveConfig.getMasterSlaveRule(), Maps.<String, Object>newHashMap());
        return dataSource;
    }

}

