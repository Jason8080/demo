package com.gm.demo.tools.sharding.config;

import cn.gmlee.tools.base.util.JdbcUtil;
import cn.gmlee.tools.sharding.algorithm.standard.NewestHorizontalDatabaseStandardShardingAlgorithm;
import cn.gmlee.tools.sharding.algorithm.standard.NewestHorizontalTableStandardShardingAlgorithm;
import cn.gmlee.tools.sharding.assist.MysqlShardingAssist;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.shardingsphere.core.yaml.config.sharding.YamlShardingRuleConfiguration;
import org.apache.shardingsphere.core.yaml.config.sharding.YamlShardingStrategyConfiguration;
import org.apache.shardingsphere.core.yaml.config.sharding.YamlTableRuleConfiguration;
import org.apache.shardingsphere.core.yaml.config.sharding.strategy.YamlStandardShardingStrategyConfiguration;
import org.apache.shardingsphere.core.yaml.swapper.ShardingRuleConfigurationYamlSwapper;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
import org.apache.shardingsphere.shardingjdbc.spring.boot.sharding.ShardingRuleCondition;
import org.apache.shardingsphere.transaction.spring.ShardingTransactionTypeScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Jas°
 * @date 2021/6/10 (周四)
 */
@Configuration
@ComponentScan("org.apache.shardingsphere.spring.boot.converter")
@EnableAutoConfiguration(exclude = SpringBootConfiguration.class)
public class ShardingAutoConfiguration {

    @Resource
    private DataSource dataSource;

    @Value("${tools.sharding.sysId:0}")
    private Long sysId;

    @Value("${tools.sharding.database.username:root}")
    private String username;

    @Value("${tools.sharding.database.password:root}")
    private String password;


    @Bean
    @Primary
    @Conditional(ShardingRuleCondition.class)
    public DataSource shardingDataSource() throws Exception {
        List<Map<String, String>> list = JdbcUtil.exec(dataSource, MysqlShardingAssist.SELECT_DATABASES_SQL, String.class);
        List<String> allDatabases = list.stream().map(map -> map.values().stream().findFirst().get()).collect(Collectors.toList());
        List<Map<String, Object>> listMaps = JdbcUtil.exec(dataSource, String.format(MysqlShardingAssist.SELECT_TOOLS_SHARDING_CONFIG_SQL, sysId));
        List<String> databases = allDatabases.stream().filter(x -> Pattern.matches("db[0-9]+", x)).collect(Collectors.toList());
        List<String> all = new ArrayList<>();
        for (String db : databases){
            List<Map<String, String>> maps = JdbcUtil.exec(dataSource, String.format(MysqlShardingAssist.SELECT_TABLES_SQL, db), String.class);
            List<String> allTables = maps.stream().map(map -> db + "." + map.values().stream().findFirst().get()).collect(Collectors.toList());
            all.addAll(allTables);
        }

        YamlShardingRuleConfiguration src = new YamlShardingRuleConfiguration();
        Map<String, YamlTableRuleConfiguration> tables = src.getTables();
        YamlTableRuleConfiguration trc = new YamlTableRuleConfiguration();
        YamlShardingStrategyConfiguration databaseStrategy = new YamlShardingStrategyConfiguration();
        YamlStandardShardingStrategyConfiguration standardDb = new YamlStandardShardingStrategyConfiguration();
        standardDb.setShardingColumn("date");
        standardDb.setPreciseAlgorithmClassName(NewestHorizontalDatabaseStandardShardingAlgorithm.class.getName());
        standardDb.setRangeAlgorithmClassName(NewestHorizontalDatabaseStandardShardingAlgorithm.class.getName());
        databaseStrategy.setStandard(standardDb);
        YamlShardingStrategyConfiguration tableStrategy = new YamlShardingStrategyConfiguration();
        YamlStandardShardingStrategyConfiguration standardTab = new YamlStandardShardingStrategyConfiguration();
        standardTab.setShardingColumn("date");
        standardTab.setPreciseAlgorithmClassName(NewestHorizontalTableStandardShardingAlgorithm.class.getName());
        standardTab.setRangeAlgorithmClassName(NewestHorizontalTableStandardShardingAlgorithm.class.getName());
        tableStrategy.setStandard(standardTab);
        trc.setLogicTable("t_tab");
        trc.setActualDataNodes(String.join(",", all));
        trc.setDatabaseStrategy(databaseStrategy);
        trc.setTableStrategy(tableStrategy);

        tables.put(trc.getLogicTable(), trc);

        Map<String, DataSource> dataSourceMap = new HashMap(databases.size());
        for (String db : databases) {
            Map<String,String> map = new HashMap(4);
            map.put("driverClassName", "com.mysql.cj.jdbc.Driver");
            map.put("url", String.format("jdbc:mysql://127.0.0.1:3306/%s?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai", db));
            map.put("username", "root");
            map.put("password", "root");
            DataSource dataSource = DruidDataSourceFactory.createDataSource(map);
            dataSourceMap.put(db, dataSource);
        }
        Properties props = new Properties();
        props.setProperty("sql.show", "true");
        return ShardingDataSourceFactory.createDataSource(dataSourceMap, new ShardingRuleConfigurationYamlSwapper().swap(src), props);
    }

    @Bean
    public ShardingTransactionTypeScanner shardingTransactionTypeScanner() {
        return new ShardingTransactionTypeScanner();
    }
}
