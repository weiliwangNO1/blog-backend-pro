package com.cherry.blog.util.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author weili.wang
 * @version 1.0
 * @date 2024/3/10 9:24
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "spring.blog")
public class BlogEnvironment {

    /**
     * 环境前缀
     */
    private String environment;

}
