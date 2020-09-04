package com.gm.demo.nacos.server.common.config.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * redis 分布式ID生成器
 *
 * @author Timi
 * @date 2020 /8/28 (周五)
 */
public class RedisId {
    private String prefix;
    private Integer expire;
    private RedisTemplate redisTemplate;

    public RedisId(RedisTemplate redisTemplate, String prefix, Integer expire) {
        this.prefix = prefix;
        this.expire = expire;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 生成自增ID
     *
     * @param key the key
     * @return the long
     */
    public long generate(String key) {
        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        return counter.incrementAndGet();
    }

    /**
     * 生成自增ID (自动过期)
     *
     * @param key        the key
     * @param expireTime the expire time
     * @return the long
     */
    public long generate(String key, Date expireTime) {
        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        counter.expireAt(expireTime);
        return counter.incrementAndGet();
    }

    /**
     * 生成增量ID
     *
     * @param key       the key
     * @param increment the increment
     * @return the long
     */
    public long generate(String key, int increment) {
        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        return counter.addAndGet(increment);
    }

    /**
     * 生成增量ID (自动过期)
     *
     * @param key        the key
     * @param increment  the increment
     * @param expireTime the expire time
     * @return the long
     */
    public long generate(String key, int increment, Date expireTime) {
        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        counter.expireAt(expireTime);
        return counter.addAndGet(increment);
    }

    /**
     * 生成当天指定长度的ID (自动过期)
     * <p>
     * key = order, length = 5 ,当天日期2050年1月1日
     * 结果: 2050010100001
     * </p>
     *
     * @param key    the key
     * @param length the length
     * @return the string
     */
    public String generateToday(String key, Integer length) {
        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        long num = counter.incrementAndGet();
        counter.expireAt(getTodayEndTime());
        String id = getToday() + String.format("%0" + length + "d", num);
        return id;
    }


    /**
     * 获取当天最后的时间.
     *
     * @return the today end time
     */
    public static Date getTodayEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    /**
     * 获取当天时间.
     *
     * @return the today
     */
    public static String getToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }

}