package com.cherry.blog.redis.service;

import com.cherry.blog.util.tools.ConstantValue;
import com.cherry.blog.util.tools.MagicValue;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.bcel.Const;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * redis map
 * @author weiliwang
 * @date 2023/12/17
 */
@Slf4j
public abstract class AbstractBaseRedis<K, V> implements BaseRedis<K, V>{

    @Autowired
    private RedissonClient redissonClient;


    /**
     * 加锁
     * @param key
     */
    private void lock(K key) {
        try {
            log.info("lock. key={}", key);
            redissonClient.getLock(getLockKey(key)).lock(MagicValue.TEN, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("lock error. key={}", key);
            e.printStackTrace();
        }
    }

    /**
     * 加锁（指定加锁时间）
     * @param key
     * @param lockTime
     */
    private void lock(K key, Integer lockTime) {
        try {
            log.info("lock. key={}", key);
            redissonClient.getLock(getLockKey(key)).lock(MagicValue.TEN, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("lock error. key={}", key);
            e.printStackTrace();
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
     * 尝试加锁（指定加锁时间）
     * @param key
     * @param lockTime
     * @return
     */
    private Boolean tryLock(K key, Integer lockTime) {
        try {
            log.info("tryLock. key={}", key);
            return redissonClient.getLock(getLockKey(key)).tryLock(MagicValue.ONE, lockTime, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("tryLock error. key={}", key);
            e.printStackTrace();
            return ConstantValue.FALSE;
        }
    }

    /**
     * 存放数据
     * @param key
     * @param value
     */
    public Boolean putValueTryLock(K key, V value) {
        if(tryLock(key)) {
            Boolean tag = ConstantValue.FALSE;
            try {
                redissonClient.getMap(getMapName()).fastPut(key, value);
                log.info("putValueTryLock. key={}, value={}", key, value);
                tag = ConstantValue.TRUE;
            } catch (Exception e) {
                log.info("putValueTryLock error. key={}, value={}", key, value);
                e.printStackTrace();
                tag = ConstantValue.FALSE;
            } finally {
                unlock(key);
            }
            return tag;
        }
        return ConstantValue.FALSE;
    }

    /**
     * 存放数据
     * @param key
     * @param value
     */
    public void putValueLock(K key, V value) {
        try {
            lock(key);
            redissonClient.getMap(getMapName()).fastPut(key, value);
            log.info("putValueTryLock. key={}, value={}", key, value);
        } catch (Exception e) {
            log.info("putValueTryLock error. key={}, value={}", key, value);
            e.printStackTrace();
        } finally {
            unlock(key);
        }
    }

    /**
     * 获取数据
     * @param key
     * @return
     */
    public V getValue(K key) {
        V value = null;
        try {
            value= (V) redissonClient.getMap(getMapName()).get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return value;
    }

    /**
     * 删除数据
     * @param key
     * @return
     */
    public Boolean deleteValueTryLock(K key) {
        if(tryLock(key)) {
            Boolean tag = ConstantValue.FALSE;
            try {
                redissonClient.getMap(getMapName()).remove(key);
                log.info("deleteValueTryLock. key={}", key);
                tag = ConstantValue.TRUE;
            } catch (Exception e) {
                log.info("deleteValueTryLock error. key={}", key);
                e.printStackTrace();
                tag = ConstantValue.FALSE;
            } finally {
                unlock(key);
            }
            return tag;
        }
        return ConstantValue.FALSE;
    }


    /**
     * 删除数据
     * @param key
     * @return
     */
    public void deleteValue(K key) {
        try {
            lock(key);
            redissonClient.getMap(getMapName()).remove(key);
            log.info("deleteValue. key={}", key);
        } catch (Exception e) {
            log.info("deleteValue error. key={}", key);
            e.printStackTrace();
        } finally {
            unlock(key);
        }
    }

}
