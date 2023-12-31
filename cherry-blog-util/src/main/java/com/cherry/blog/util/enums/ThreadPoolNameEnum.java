package com.cherry.blog.util.enums;

/**
 * 线程池名称枚举（定义名称不能超过13位）
 * @author weili.wang
 * @date 2023/12/31
 */
public enum ThreadPoolNameEnum {

    /**
     * 默认线程池名称
     */
    CHERRY_BLOG_THREAD_POOL("blog-thread", "默认线程池名称");

    /**
     * 线程池名称
     */
    private String poolName;
    /**
     * 线程池描述
     */
    private String poolDesc;

    ThreadPoolNameEnum(String poolName, String poolDesc) {
        this.poolName = poolName;
        this.poolDesc = poolDesc;
    }

    /**
     * 获取线程池名称
     * @return
     */
    public String getPoolName() {
        return this.poolName;
    }
}
