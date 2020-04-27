package com.gm.redis.lock;

import java.util.UUID;

/**
 * RedisLockApplication.
 *
 * @author : Jason.lee
 * @version : 1.0
 */
public class RedisLockApplication {


    /**
     * 测试分布式锁工具
     * @param args
     */
    public static void main(String[] args) {
        String orderId = LockJ.exec("双11-8001商品抢购", (String x) -> {
            System.out.println("这里可以写拿到锁要执行的代码, 比如减库存。");
            return UUID.randomUUID().toString();
        });
        System.out.println(orderId);
    }
}
