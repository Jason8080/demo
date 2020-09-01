package com.gm.demo.nacos.server.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 分布式锁
 *
 * @author Timi
 * @date 2020 /8/28 (周五)
 */
@Component
public class RedisLock {

    @Value("${redis.lock-prefix:REDIS:LOCK:}")
    public String lockPrefix;
    @Value("${redis.lock-expire:300}")
    public int lockExpire = 300;

    /**
     * The Redis template.
     */
    @Autowired
    RedisTemplate redisTemplate;


    /**
     * 最终加强分布式锁
     *
     * @param key key值
     * @return 是否获取到 boolean
     */
    public boolean lock(String key){
        String lock = lockPrefix + key;
        // 利用lambda表达式
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
 
            long expireAt = System.currentTimeMillis() + lockExpire + 1;

            if (connection.setNX(lock.getBytes(), String.valueOf(expireAt).getBytes())) {
                return true;
            } else {
 
                byte[] value = connection.get(lock.getBytes());

                if (Objects.nonNull(value) && value.length > 0) {
 
                    long expireTime = Long.parseLong(new String(value));
 
                    if (expireTime < System.currentTimeMillis()) {
                        // 如果锁已经过期
                        byte[] oldValue = connection.getSet(lock.getBytes(), String.valueOf(System.currentTimeMillis() + lockExpire + 1).getBytes());
                        // 防止死锁
                        return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                    }
                }
            }
            return false;
        });
    }

    /**
     * 删除锁
     *
     * @param key the key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }
 
}