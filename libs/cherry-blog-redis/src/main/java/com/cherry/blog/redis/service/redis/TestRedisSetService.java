package com.cherry.blog.redis.service.redis;

import com.cherry.blog.redis.model.User;
import com.cherry.blog.util.tools.ConstantValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author weili.wang
 * @version 1.0
 * @date 2024/3/9 21:39
 */
@Slf4j
@Service
public class TestRedisSetService extends AbstractBaseRedisSet<Integer, User>{

    /**
     * 测试redis 表名称
     */
    private static final String TEST_SET_NAME = "blog_test_set";

    @Override
    public String getSetName() {
        return TEST_SET_NAME;
    }

    @Override
    public String getLockKey(Integer key) {
        return TEST_SET_NAME.concat(ConstantValue.SPLIT_STR1).concat(key.toString());
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
