package com.gm.demo.nacos.server.common.config.redis;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;

/**
 * 分布式锁
 *
 * @author Timi
 * @date 2020 /8/28 (周五)
 */
public class RedisLock{
    private String prefix;
    private Integer expire;
    private RedisTemplate redisTemplate;

    public RedisLock(RedisTemplate<String, String> redisTemplate, String prefix, Integer expire) {
        this.prefix = prefix;
        this.expire = expire;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 分布式锁 (自动过期)
     *
     * @param key key值
     * @return 是否获取到 boolean
     */
    public boolean lock(String key){
        String lock = prefix + key;
        // 利用lambda表达式
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
 
            long expireAt = System.currentTimeMillis() + expire + 1;

            if (connection.setNX(lock.getBytes(), String.valueOf(expireAt).getBytes())) {
                return true;
            } else {
 
                byte[] value = connection.get(lock.getBytes());

                if (Objects.nonNull(value) && value.length > 0) {

                    long expireTime = Long.parseLong(new String(value));
 
                    if (expireTime < System.currentTimeMillis()) {
                        // 如果锁已经过期
                        byte[] oldValue = connection.getSet(lock.getBytes(), String.valueOf(System.currentTimeMillis() + expire + 1).getBytes());
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
     * @return
     */
    public void unlock(String key, String val) {
        String lock = prefix + key;
        redisTemplate.execute((RedisCallback) connection -> {
            byte[] bytes = connection.get(lock.getBytes());
            if(Objects.nonNull(bytes) && bytes.length > 0) {
                // 防止解别人的锁
                String old = new String(bytes);
                if(old.equals(val)) {
                    connection.del(lock.getBytes());
                    return true;
                }
            }
            return false;
        });
    }

    private boolean setNX(String key, String val){
        String lock = prefix + key;
        return (Boolean) redisTemplate.execute((RedisCallback) connection
                -> connection.setNX(lock.getBytes(), val.getBytes()));
    }

    /**
     * 分布式锁 (自动解锁).
     *
     * @param key the key
     * @param run the run
     */
    public void lock(String key, Runnable run){
        String val = UUID.randomUUID().toString();
        if(setNX(key, val)){
            try {
                run.run();
            } finally {
                unlock(key, val);
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
    public <T> T lock(String key, Supplier<T> run){
        String val = UUID.randomUUID().toString();
        if(setNX(key, val)){
            try {
                return run.get();
            } finally {
                unlock(key, val);
            }
        }
        return null;
    }
}