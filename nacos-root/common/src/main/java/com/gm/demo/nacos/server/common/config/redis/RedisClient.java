package com.gm.demo.nacos.server.common.config.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * redis 工具类
 *
 * @author Timi
 * @date 2020 /8/28 (周五)
 */
public class RedisClient<K,V> {
	private String prefix;
	private Integer expire;
	private RedisTemplate redisTemplate;

	public <V, K> RedisClient(RedisTemplate<K, V> redisTemplate, String prefix, Integer expire) {
		this.prefix = prefix;
		this.expire = expire;
		this.redisTemplate = redisTemplate;
	}

	/**
	 * Set.
	 *
	 * @param key the key
	 * @param val the val
	 * @throws Exception the exception
	 */
	public void set(K key, V val) {
		 ValueOperations<K,V> ops = redisTemplate.opsForValue();
		 ops.set(key, val);
	}

	/**
	 * Set boolean.
	 *
	 * @param key          the key
	 * @param val          the val
	 * @param expireSecond the expire second
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean set(K key, V val, long expireSecond) {
		 ValueOperations<K,V> ops = redisTemplate.opsForValue();
		 ops.set(key, val);
		 return redisTemplate.expire(key, expireSecond, TimeUnit.SECONDS);
	}

	/**
	 * Get object.
	 *
	 * @param key the key
	 * @return the object
	 * @throws Exception the exception
	 */
	public V get(K key) {
		 ValueOperations<K,V> ops = redisTemplate.opsForValue();
		 return ops.get(key);
	}

	/**
	 * Exists boolean.
	 *
	 * @param key the key
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean exists(K key) throws Exception {
		return redisTemplate.hasKey(key);
	}
}
