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
    public static final String LOADBALANCER = "org.redisson.connection.balancer.RoundRobinLoadBalancer";

}
