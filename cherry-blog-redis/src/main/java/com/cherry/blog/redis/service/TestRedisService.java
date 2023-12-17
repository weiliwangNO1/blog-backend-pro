package com.cherry.blog.redis.service;

import com.cherry.blog.redis.model.User;
import com.cherry.blog.util.tools.ConstantValue;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 测试redis
 * @author weiliwang
 * @author
 */
@Service
public class TestRedisService extends AbstractBaseRedis<Integer, User>{

    /**
     * 测试redis 表名称
     */
    private static final String testMapName = "blog_test_map";

    @Override
    public String getMapName() {
        return testMapName;
    }

    @Override
    public String getLockKey(Integer key) {
        return testMapName.concat(ConstantValue.SPLIT_STR1).concat(key.toString());
    }

    @PostConstruct
    public void initData() {
        User user = User.builder()
                .id(1)
                .name("weiliwang")
                .age(28)
                .build();
        User user2 = User.builder()
                .id(2)
                .name("xaioyu")
                .age(28)
                .build();
        putValueTryLock(1, user);
        putValueTryLock(2, user2);
    }
}
