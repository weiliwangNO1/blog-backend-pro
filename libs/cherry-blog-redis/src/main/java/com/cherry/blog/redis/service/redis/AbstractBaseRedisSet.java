package com.cherry.blog.redis.service.redis;

import com.cherry.blog.redis.model.User;
import com.cherry.blog.util.tools.ConstantValue;
import com.cherry.blog.util.tools.MagicValue;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author weili.wang
 * @version 1.0
 * @date 2024/3/9 21:34
 */
@Slf4j
public abstract class AbstractBaseRedisSet<K, V> extends AbstractBaseHotKeyRedis<K, V> implements BaseRedisSet<K, V>{

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 默认7天过期
     */
    private static Long KEY_EXPIRE_SECONDS = 60*60*24*7L;

    @Override
    public Long getKeyExpireSeconds() {
        return KEY_EXPIRE_SECONDS;
    }

    /**
     * 尝试加锁
     * @param key
     * @return
     */
    private Boolean tryLock(K key) {
        try {
            log.info("tryLock. key={}", key);
            return redissonClient.getLock(getLockKey(key)).tryLock(MagicValue.ONE, MagicValue.TEN, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("tryLock error. key={}", key);
            e.printStackTrace();
            return ConstantValue.FALSE;
        }
    }

    /**
     * 解锁
     * @param key
     */
    private void unlock(K key) {
        try {
            log.info("unlock. key={}", key);
            redissonClient.getLock(getLockKey(key)).unlock();
        } catch (Exception e) {
            log.error("unlock error. key={}", key);
            e.printStackTrace();
        }
    }

    /**
     * 存放数据（建议使用）
     * @param key
     * @param value
     */
    public Boolean putValueTryLock(K key, V value) {
        if(tryLock(key)) {
            StopWatch stopWatch = new StopWatch("Distributed_Locks_PutValueTryLock_Set");
            stopWatch.start("PutValueTryLock_Set");
            Boolean tag = ConstantValue.FALSE;
            try {
                RSet<V> rSet = redissonClient.getSet(getSetName());
                if(getKeyExpireSeconds() != null || getKeyExpireSeconds() > 0) {
                    rSet.expire(getKeyExpireSeconds(), TimeUnit.SECONDS);
                }
                rSet.add(value);
                statisticsRedisHotKey(getSetName());
                statisticsRedisValueHotKey(getSetName(), key);
                log.info("putValueTryLock_Set. key={}, value={}", key, value);
                tag = ConstantValue.TRUE;
            } catch (Exception e) {
                log.info("putValueTryLock_Set error. key={}, value={}", key, value);
                e.printStackTrace();
                tag = ConstantValue.FALSE;
            } finally {
                unlock(key);
                stopWatch.stop();
                log.info("putValueTryLock_Set cost time={}ms", stopWatch.getTotalTimeMillis());
            }
            return tag;
        }
        return ConstantValue.FALSE;
    }

}
