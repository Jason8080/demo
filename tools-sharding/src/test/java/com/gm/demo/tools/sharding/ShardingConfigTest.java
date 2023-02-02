package com.gm.demo.tools.sharding;

import cn.hll.tools.sharding.server.ShardingServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * The type User test.
 */
@ActiveProfiles("loc")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class ShardingConfigTest {

    @Resource
    private ShardingServer shardingServer;

    @Test
    @Rollback(false)
    public void addConf(){
        shardingServer.initializeTableShardingRule(0L, "db", "tab");
    }

    @Test
    @Rollback(false)
    public void releaseConf(){
        shardingServer.releaseActualDataNodes(6L, "db", "tab");
    }
}