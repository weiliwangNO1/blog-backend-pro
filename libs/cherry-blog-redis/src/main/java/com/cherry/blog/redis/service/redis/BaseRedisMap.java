package com.cherry.blog.redis.service.redis;

/**
* redisson 接口
* @author weili.wang
* @version 1.0
* @date 2024/03/09 20:17
*/
public interface BaseRedisMap<K, V> extends BaseRedis<K, V> {

    /**
     * 获取redis map表名
     * @return
     */
    String getMapName();


}
