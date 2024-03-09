package com.cherry.blog.redis.service.redis;

/**
 * @author weili.wang
 * @version 1.0
 * @date 2024/3/9 20:46
 */
public interface BaseRedisSet<K, V> extends BaseRedis<K, V> {

    /**
     * 获取set名称
     * @param: 
     * @return java.lang.String
     * @author weili.wang
     * @date 2024-03-09 20:47
     */
    String getSetName();

}
