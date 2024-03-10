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
 * @date 2024/3/9 23:46
 */
@Slf4j
@Getter
@Setter
@Component
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "spring.kafka.consumer")
public class KafkaConsumerConfig {

    /**
     * 是否自动提交消息
     */
    private String enableAutoCommit;

    /**
     * kafka集群地址
     */
    private String bootstrapServers;

    /**
     * groupId  消费者组名称
     */
    private String groupId;

    /**
     * Kafka中没有初始偏移或如果当前偏移在服务器上不再存在时,默认区最新 ，有三个选项 【latest, earliest, none】
     */
    private String autoOffsetReset;

    /**
     * 指定消息key和消息体的序列化方式
     */
    private String keyDeserializer;

    /**
     * 指定消息key和消息体的序列化方式
     */
    private String valueDeserializer;



}
