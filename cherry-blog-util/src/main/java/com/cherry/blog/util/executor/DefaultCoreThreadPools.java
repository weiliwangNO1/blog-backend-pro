package com.cherry.blog.util.executor;

import com.cherry.blog.util.enums.ThreadPoolExecutorEnum;
import com.cherry.blog.util.enums.ThreadPoolNameEnum;
import com.cherry.blog.util.tools.MagicValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * 固定线程数个数
 * @author weili.wang
 * @date 2023/12/31
 */
@Slf4j
@Service
public class DefaultCoreThreadPools extends AbstractThreadPoolExecutor{

    /**
     * 线程池名称
     */
    private static final String THREAD_POOL_NAME = ThreadPoolNameEnum.CHERRY_BLOG_THREAD_POOL.getPoolName();

    /**
     * 线程池类型
     */
    private static final ThreadPoolExecutorEnum THREAD_POOL_EXECUTOR_ENUM = ThreadPoolExecutorEnum.NEW_CACHED_THREAD_POOL;

    /**
     * 线程默认20
     */
    private static final Integer CORE_SIZE = MagicValue.TWENTY;


    /**
     * 阻塞队列长度
     */
    private static final Integer BLOCKING_QUEUE_SIZE = MagicValue.THREE_HUNDRED;

    /**
     * 最大线程池数量
     */
    private static final Integer MAX_I_NUM_POOL_SIZE = MagicValue.HUNDRED;

    /**
     * 线程存活时间
     */
    private static final Integer KEEP_ALIVE_TIME = MagicValue.SIXTY;

    /**
     * 存活时间单位
     */
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    /**
     * 默认拒绝策略
     */
    private static final RejectedExecutionHandler REJECTED_EXECUTION_HANDLER = new ThreadPoolExecutor.AbortPolicy();

    @Override
    public String getThreadPoolName() {
        return THREAD_POOL_NAME;
    }

    @Override
    public ThreadPoolExecutorEnum getThreadPoolType() {
        return THREAD_POOL_EXECUTOR_ENUM;
    }

    @Override
    public Integer getCoreSize() {
        return CORE_SIZE;
    }

    @Override
    public Integer getBlockingQueueSize() {
        return BLOCKING_QUEUE_SIZE;
    }

    @Override
    public Integer getMaximumPoolSize() {
        return MAX_I_NUM_POOL_SIZE;
    }

    @Override
    public Integer getKeepAliveTime() {
        return KEEP_ALIVE_TIME;
    }

    @Override
    public TimeUnit getTimeUnit() {
        return TIME_UNIT;
    }

    @Override
    public RejectedExecutionHandler getRejectedExecutionHandler() {
        return REJECTED_EXECUTION_HANDLER;
    }
}
