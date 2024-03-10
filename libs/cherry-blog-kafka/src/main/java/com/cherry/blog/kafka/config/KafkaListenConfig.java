package com.cherry.blog.kafka.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author weili.wang
 * @version 1.0
 * @date 2024/3/10 9:13
 */
@Slf4j
@Getter
@Setter
@Component
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "spring.kafka.listener")
public class KafkaListenConfig {

    /**
     * 在侦听器容器中运行的线程数。
     */
    private Integer concurrency;

    /**
     * listner负责ack，每调用一次，就立即commit
     */
    private String ackMode;

    /**
     *
     */
    private String missingTopicsFatal;

    /**
     *
     */
    private String type;

}
