package com.gm.redis.lock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Objects;
import java.util.UUID;

/**
 * LockJ.
 *
 * @author : Jason.lee
 * @version : 1.0
 */
public class LockJ {
    private static final JedisPool jp = new JedisPool();

    public synchronized static String getLock() {
        return getLock("LockJ", 10);
    }

    public synchronized static boolean lock() {
        return lock("LockJ", 10);
    }

    public synchronized static String getLock(String key) {
        return getLock(key, 1);
    }

    public synchronized static boolean lock(String key) {
        return lock(key, 1);
    }

    public synchronized static boolean lock(String key, int seconds) {
        String lock = getLock(key, seconds);
        return lock != null;
    }

    public synchronized static String getLock(String key, int seconds) {
        Jedis jedis = jp.getResource();
        if (jedis.ttl(key) == -1) {
            jedis.expire(key, seconds);
        }
        String uuid = UUID.randomUUID().toString();
        try {
            if (jedis.setnx(key, uuid) == 1) {
                jedis.expire(key, seconds);
                return uuid;
            }
        } finally {
            jedis.close();
        }
        return null;
    }

    public synchronized static void unlock(String key) {
        Jedis jedis = jp.getResource();
        try {
            jedis.del(key);
        } finally {
            jedis.close();
        }
    }

    public synchronized static boolean unlock(String key, String value) {
        Jedis jedis = jp.getResource();
        try {
            String s = jedis.get(key);
            if (Objects.equals(s, value) && jedis.del(key) > 0) {
                return true;
            }
        } finally {
            jedis.close();
        }
        return false;
    }
}
