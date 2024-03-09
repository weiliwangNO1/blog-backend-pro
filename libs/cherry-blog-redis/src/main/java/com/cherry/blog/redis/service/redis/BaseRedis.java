package com.cherry.blog.redis.service.redis;

/**
 * redis服务
 * @author weili.wang
 * @version 1.0
 * @date 2024/3/9 20:45
 */
public interface BaseRedis<K, V> {

    /**
     * 获取redis lock
     * @param key
     * @return
     */
    String getLockKey(K key);

    /**
     * 设置过期时间
     * @return
     */
    Long getKeyExpireSeconds();

}
