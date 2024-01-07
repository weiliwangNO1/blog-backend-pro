package com.cherry.blog.redis.service.redis;

import com.cherry.blog.redis.model.User;
import com.cherry.blog.redis.service.redis.AbstractBaseRedisMap;
import com.cherry.blog.util.executor.DefaultCoreThreadPools;
import com.cherry.blog.util.tools.ConstantValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 测试redis
 * @author weiliwang
 * @author
 */
@Slf4j
@Service
public class TestRedisService extends AbstractBaseRedisMap<Integer, User> {

    @Autowired
    private DefaultCoreThreadPools defaultCoreThreadPools;

    /**
     * 测试redis 表名称
     */
    private static final String TEST_MAP_NAME = "blog_test_map";


    @Override
    public String getMapName() {
        return TEST_MAP_NAME;
    }

    @Override
    public String getLockKey(Integer key) {
        return TEST_MAP_NAME.concat(ConstantValue.SPLIT_STR1).concat(key.toString());
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

        this.threadPool();
    }

    /**
     * 测试线程池
     */
    private void threadPool() {
        ThreadPoolExecutor threadPoolExecutor = defaultCoreThreadPools.getDefaultThreadPool();
        List<String> nameList = Arrays.asList("weili.wang", "taoyang", "junwu", "chengyang");
        nameList.forEach(item -> {
            threadPoolExecutor.execute(() -> {
                log.info("print name={}", item);
            });
        });
    }
}
