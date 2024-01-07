package com.cherry.blog.redis.service.redis;

/**
 * redisson 接口
 * @author weiliwang
 * @date 2023/12/17
 */
public interface BaseRedisMap<K, V> {

    /**
     * 获取redis map表名
     * @return
     */
    String getMapName();

    /**
     * 获取redis lock
     * @param key
     * @return
     */
    String getLockKey(K key);

    /**
     * 设置map-key过期时间
     * @return
     */
    Long getKeyExpireSeconds();

}
