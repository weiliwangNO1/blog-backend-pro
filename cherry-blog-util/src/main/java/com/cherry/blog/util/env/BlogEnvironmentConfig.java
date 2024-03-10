package com.cherry.blog.util.env;

import com.cherry.blog.util.enums.EnvironmentEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author weili.wang
 * @version 1.0
 * @date 2024/3/10 9:33
 */
@Slf4j
@Component
public class BlogEnvironmentConfig {

    @Autowired
    private Environment environment;

    /**
     * 获取环境前缀
     * @param:
     * @return java.lang.String
     * @author weili.wang
     * @date 2024-03-10 09:39
     */
    public String getEnvPrefix() {
        log.info("BlogEnvironmentConfig===getEnvPrefix find envPrefix");
        return environment.getProperty(EnvironmentEnum.BLOG_ENVIRONMENT.getCode());
    }

}
