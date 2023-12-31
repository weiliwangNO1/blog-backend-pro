package com.cherry.blog.util.enums;

/**
 * 线程组名称
 * @author weili.wang
 * @date 2023/12/31
 */
public enum ThreadPoolGroupNameEnum {

    /**
     * 默认线程池名称
     */
    CHERRY_BLOG_THREAD_POOL_GROUP("cherry_blog_thread_pool_group", "默认线程池组名");

    /**
     * 线程池名称
     */
    private String groupName;
    /**
     * 线程池描述
     */
    private String groupDesc;


    ThreadPoolGroupNameEnum(String groupName, String groupDesc) {
        this.groupName = groupName;
        this.groupDesc = groupDesc;
    }

    /**
     * 获取当前线程池组名
     * @return
     */
    public String getGroupName() {
        return this.groupName;
    }
}
