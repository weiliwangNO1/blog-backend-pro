package com.cherry.blog.util.executor;

import com.cherry.blog.util.enums.ThreadPoolExecutorEnum;

import java.util.concurrent.*;

/**
 * 线程池
 * @author weili.wang
 * @date 2023/12/31
 */
public interface BaseThreadPoolExecutor {

    /**
     * 指定线程池名称
     * @return
     */
    String getThreadPoolName();

    /**
     * 获取线程池类型
     * @return
     */
    ThreadPoolExecutorEnum getThreadPoolType();

    /**
     * 线程池大小
     * @return
     */
    Integer getCoreSize();

    /**
     * 阻塞队列大小
     * @return
     */
    Integer getBlockingQueueSize();

    /**
     * 最大线程数
     * @return
     */
    Integer getMaximumPoolSize();

    /**
     * 存活时间
     * @return
     */
    Integer getKeepAliveTime();

    /**
     * 时间单位
     * @return
     */
    TimeUnit getTimeUnit();

    /**
     * 拒绝策略
     * @return
     */
    RejectedExecutionHandler getRejectedExecutionHandler();
}
