package com.cherry.blog.redis.config;

/**
 * redisson编码
 * @author weiliwang
 * @date 2023/12/17
 */
public enum RedissonCodeConfig {

    /**
     * json方式
     */
    JsonJacksonCodec,
    /**
     * 字符串方式
     */
    StringCodec;

}
