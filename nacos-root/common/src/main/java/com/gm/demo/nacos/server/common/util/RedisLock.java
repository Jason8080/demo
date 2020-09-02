package com.gm.demo.nacos.server.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * 分布式锁
 *
 * @author Timi
 * @date 2020 /8/28 (周五)
 */
@Component
public class RedisLock {

    /**
     * The Lock prefix.
     */
    @Value("${redis.lock-prefix:REDIS:LOCK:}")
    private String lockPrefix;
    /**
     * The Lock expire.
     */
    @Value("${redis.lock-expire:300}")
    private int lockExpire = 300;

    /**
     * The Redis template.
     */
    @Autowired
    RedisTemplate redisTemplate;


    /**
     * 分布式锁 (自动过期)
     *
     * @param key key值
     * @return 是否获取到 boolean
     */
    public synchronized boolean lock(String key){
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
     * 解锁 (删除)
     *
     * @param key the key
     */
    public void unlock(String key) {
        redisTemplate.delete(key);
    }


    private boolean setNX(String key){
        String lock = lockPrefix + key;
        return (Boolean) redisTemplate.execute((RedisCallback) connection
                -> connection.setNX(lock.getBytes(), new byte[0]));
    }

    /**
     * 分布式锁 (自动解锁).
     *
     * @param key the key
     * @param run the run
     */
    public synchronized void lock(String key, Runnable run){
        if(setNX(key)){
            try {
                run.run();
            } finally {
                unlock(key);
            }
        }
    }

    /**
     * 分布式锁 (自动解锁).
     *
     * @param <T> the type parameter
     * @param key the key
     * @param run the run
     * @return the t
     */
    public synchronized <T> T lock(String key, Supplier<T> run){
        if(setNX(key)){
            try {
                return run.get();
            } finally {
                unlock(key);
            }
        }
        return null;
    }
}