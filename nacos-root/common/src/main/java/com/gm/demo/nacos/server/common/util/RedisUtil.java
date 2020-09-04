package com.gm.demo.nacos.server.common.util;

import com.gm.demo.nacos.server.common.config.redis.RedisClient;
import com.gm.demo.nacos.server.common.config.redis.RedisId;
import com.gm.demo.nacos.server.common.config.redis.RedisLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Redis通用工具 (必须使用SpringBean有效)
 *
 * @author Timi.lee
 * @date 2020/9/4 (周五)
 */
@Component
public class RedisUtil<K,V> {

    @Value("${redis.common.prefix:REDIS:COMMON:}")
    private String prefix;
    @Value("${redis.common.expire:300}")
    private Integer expire = 300;

    private RedisClient<K,V> redisClient;
    private RedisLock redisLock;
    private RedisId redisId;

    public RedisUtil(RedisTemplate redisTemplate) {
        redisClient = new RedisClient<K,V>(redisTemplate, prefix, expire);
        redisLock = new RedisLock(redisTemplate, prefix, expire);
        redisId = new RedisId(redisTemplate, prefix, expire);
    }

    public RedisClient<K,V> rc(){
        return redisClient;
    }

    public RedisLock rl(){
        return redisLock;
    }

    public RedisId ri(){
        return redisId;
    }
}
