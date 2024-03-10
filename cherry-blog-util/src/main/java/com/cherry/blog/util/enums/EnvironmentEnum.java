package com.cherry.blog.util.enums;

/**
 * @author weili.wang
 * @version 1.0
 * @date 2024/3/10 9:35
 */
public enum EnvironmentEnum {

    /**
     * 环境前缀
     */
    BLOG_ENVIRONMENT("spring.blog.environment", "环境前缀");

    /**
     * 环境属性
     */
    private String code;

    /**
     * 描述
     */
    private String desc;

    EnvironmentEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }
}
