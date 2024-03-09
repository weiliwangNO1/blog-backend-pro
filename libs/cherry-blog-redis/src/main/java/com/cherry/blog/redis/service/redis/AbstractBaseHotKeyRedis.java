package com.cherry.blog.redis.service.redis;

import com.cherry.blog.util.tools.ConstantValue;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @author weili.wang
 * @version 1.0
 * @date 2024/3/9 20:54
 */
public abstract class AbstractBaseHotKeyRedis<K, V> implements BaseRedis<K, V> {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 默认360天过期
     */
    private static Long KEY_EXPIRE_SECONDS = 60*60*24*360L;

    /**
     * redis热key统计
     */
    String REDIS_HOT_KEY_NAME = "cherry_blog_hot_key";

    /**
     * redis热key统计2
     */
    String REDIS_HOT_KEY_VALUE = "cherry_blog_hot_key_value";

    /**
     * 格式
     */
    private static JsonJacksonCodec jsonJacksonCodec;

    static {
        jsonJacksonCodec = new JsonJacksonCodec();
    }

    @Override
    public Long getKeyExpireSeconds() {
        return KEY_EXPIRE_SECONDS;
    }

    /**
     * redis热key统计（表访问频次）
     * @param: redisName
     * @return void
     * @author weili.wang
     * @date 2024-03-09 21:05
     */
    public void statisticsRedisHotKey(String redisName) {
        RScoredSortedSet<String> scoredSortedSet = redissonClient.getScoredSortedSet(REDIS_HOT_KEY_NAME, jsonJacksonCodec);
        scoredSortedSet.expire(getKeyExpireSeconds(), TimeUnit.SECONDS);
        Double score = scoredSortedSet.getScore(redisName);
        if(score == null) {
            score =0.0;
        }
        double newScore = score + 1.0;
        scoredSortedSet.add(newScore, redisName);
    }

    /**
     * redis热key统计（值使用频次）
     * @param: redisName
     * @param: k
     * @return void
     * @author weili.wang
     * @date 2024-03-09 21:05
     */
    public void statisticsRedisValueHotKey(String redisName, K k) {
        RScoredSortedSet<String> scoredSortedSet = redissonClient.getScoredSortedSet(REDIS_HOT_KEY_VALUE, jsonJacksonCodec);
        scoredSortedSet.expire(getKeyExpireSeconds(), TimeUnit.SECONDS);
        String key = redisName + ConstantValue.COLON + k;
        Double score = scoredSortedSet.getScore(key);
        if(score == null) {
            score =0.0;
        }
        double newScore = score + 1.0;
        scoredSortedSet.add(newScore, key);

    }

}
