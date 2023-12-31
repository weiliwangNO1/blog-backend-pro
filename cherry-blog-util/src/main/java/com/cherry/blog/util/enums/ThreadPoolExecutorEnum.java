package com.cherry.blog.util.enums;

/**
 * 线程次枚举
 * @author weili.wang
 * @date 2023/12/31
 */
public enum ThreadPoolExecutorEnum {

    /**
     * 固定个数的线程池，阻塞队列最大长度Integer.MAX
     */
    NEW_FIXED_THREAD_POOL("newFixedThreadPool", "固定线程池"),
    /**
     * 缓存线程池，线程最大数Integer.MAX
     */
    NEW_CACHED_THREAD_POOL("newCachedThreadPool", "缓存现场池"),
    /**
     * 单一线程池
     */
    NEW_SINGLE_THREAD_POOL("newSingleThreadExecutor", "单一线程池"),
    /**
     * 定时线程池，线程最大数Integer.MAX
     */
    NEW_SCHEDULED_THREAD_POOL("newScheduledThreadPool", "定时线程池");

    /**
     * 线程池类型
     */
    private String poolType;
    /**
     * 线程池描述
     */
    private String poolDesc;

    ThreadPoolExecutorEnum(String poolType, String poolDesc) {
        this.poolType = poolType;
        this.poolDesc = poolDesc;
    }

    /**
     * 获取线程池
     * @return
     */
    public String getPoolType() {
        return this.poolType;
    }

}
