package com.cherry.blog.util.tools;

import java.io.Serializable;

/**
 * 定义常量
 * @author weiliwang
 * @date 2023/12/17
 */
public class ConstantValue implements Serializable {

    public static final String REDIS_STANDALONE_HOST = "redis://127.0.0.1:6379";
    public static final String REDIS_PASSWORD = "";
    public static final Boolean TRUE = true;
    public static final Boolean FALSE = false;
    public static final String JDK = "JDK";
    public static final String SLAVE = "SLAVE";
    public static final String SPLIT_STR1 = "-";
    public static final String SPLIT_STR2 = "_";
    public static final String COLON = ":";
    public static final String LOADBALANCER = "org.redisson.connection.balancer.RoundRobinLoadBalancer";
    public static final String DEFAULT_THREAD_POOL_NAME = "cherry_blog_thread_pool";
    public static final String THREAD = "thread";

    public static final String TRUE_STR = "true";
    public static final String FALSE_STR = "false";
    public static final String ES_SET_NETTY_RUNTIME_AVAILABLE_PROCESSORS = "es.set.netty.runtime.available.processors";

    public static final String ELASTICSEARCH_USE_DEFAULT_MODE = "standAlone";

}
