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

    private static final int seconds = 1;
    private static final int interval = 1;

    interface J<X> {
        X exec(X x);
    }


    public static <X> X exec(String key, int interval, J<X> j) {
        if (lock(key)) {
            return j.exec(null);
        } else {
            try {
                Thread.sleep(interval);
                return exec(key, interval, j);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static <X> X exec(String key, int interval, int count, J<X> j) {
        if (lock(key)) {
            return j.exec(null);
        } else if (count > 0) {
            try {
                Thread.sleep(interval);
                return exec(key, interval, count--, j);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static <X> X exec(String key, J<X> j) {
        return exec(key, interval, j);
    }


    private static final JedisPool jp = new JedisPool();

    public static String getLock() {
        return getLock("LockJ", seconds);
    }

    public static boolean lock() {
        return lock("LockJ", seconds);
    }

    public static String getLock(String key) {
        return getLock(key, seconds);
    }

    public static boolean lock(String key) {
        return lock(key, seconds);
    }

    public static boolean lock(String key, int seconds) {
        String lock = getLock(key, seconds);
        return lock != null;
    }

    public static String getLock(String key, int seconds) {
        Jedis jedis = jp.getResource();
        try {
            if (jedis.ttl(key) == -1) {
                jedis.expire(key, seconds);
            }
            String uuid = UUID.randomUUID().toString();
            if (jedis.setnx(key, uuid) == 1) {
                jedis.expire(key, seconds);
                return uuid;
            }
        } finally {
            jedis.close();
        }
        return null;
    }

    public static void unlock(String key) {
        Jedis jedis = jp.getResource();
        try {
            jedis.del(key);
        } finally {
            jedis.close();
        }
    }

    public static boolean unlock(String key, String value) {
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
