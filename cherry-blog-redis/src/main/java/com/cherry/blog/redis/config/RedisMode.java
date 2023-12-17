package com.cherry.blog.redis.config;

/**
 * redis模式
 * @author weiliwang
 * @date 2023/12/17
 */
public enum RedisMode {

    /**
     * redis单机模式
     */
    standAlone(1),
    /**
     * redis集群模式
     */
    cluster(2);

    private Integer type;

    RedisMode(Integer type) {
        this.type = type;
    }
}
