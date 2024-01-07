package com.cherry.blog.redis.service.redis;

import com.cherry.blog.redis.model.User;
import com.cherry.blog.util.tools.ConstantValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 授权操作
 * @author weili.wang
 * @date 2024/1/7
 */
@Slf4j
@Service
public class AuthRedisService extends AbstractBaseRedisMap<String, String>{

    /**
     * jwt_token_store
     */
    private static final String JWT_TOKEN_STORE = "jwt_token_store";

    /**
     * key过期时间
     */
    private static Long KEY_EXPIRE_SECONDS = 60*60L;

    /**
     * 获取map名称
     * @return
     */
    @Override
    public String getMapName() {
        return JWT_TOKEN_STORE;
    }

    /**
     * key
     * @param key
     * @return
     */
    @Override
    public String getLockKey(String key) {
        return JWT_TOKEN_STORE.concat(ConstantValue.SPLIT_STR1).concat(key.toString());
    }

    /**
     * 设置过期时间
     * @return
     */
    @Override
    public Long getKeyExpireSeconds() {
        return KEY_EXPIRE_SECONDS;
    }
}
