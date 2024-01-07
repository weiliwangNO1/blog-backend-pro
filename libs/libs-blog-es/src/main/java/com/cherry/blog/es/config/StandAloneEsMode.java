package com.cherry.blog.es.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * es单实例模式
 * @author weili.wang
 * @date 2024/1/6
 */
@Slf4j
@Getter
@Setter
@Component
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "elasticsearch.standalone")
public class StandAloneEsMode {

    /**
     * es主机
     */
    private String host;

    /**
     * es端口
     */
    private Integer port;

    /**
     * es通信协议
     */
    private String scheme;

    /**
     * es连接超时（毫秒）
     */
    private Integer connectTimeout;

    /**
     * es socket连接超时（毫秒）
     */
    private Integer socketTimeout;

}
