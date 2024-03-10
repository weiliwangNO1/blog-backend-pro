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
@ConfigurationProperties(prefix = "spring.kafka.producer")
public class KafkaProduceConfig {

    /**
     * kafka地址
     */
    private String bootstrapServers;

    /**
     * 发生错误后，消息重发的次数。
     */
    private Integer retries;

    /**
     * 当有多个消息需要被发送到同一个分区时，生产者会把它们放在同一个批次里。该参数指定了一个批次可以使用的内存大小，按照字节数计算。
     */
    private String batchSize;

    /**
     * 设置生产者内存缓冲区的大小。
     */
    private String bufferMemory;

    /**
     * 指定消息key和消息体的序列化方式
     */
    private String keyDeserializer;

    /**
     * 指定消息key和消息体的序列化方式
     */
    private String valueDeserializer;

    /**
     * 生产者确认消息是否被确认
     * acks=0如果设置为零，则生产者根本不会等待来自服务器的任何确认。该记录将立即添加到套接字缓冲区，并被视为已发送。在这种情况下，无法保证服务器已收到记录，重试配置也不会生效（因为客户端通常不会知道任何故障）。为每条记录返回的偏移量将始终设置为-1。
     * acks=1这意味着领导者将把记录写入其本地日志，但将在不等待所有追随者的完全确认的情况下做出响应。在这种情况下，如果领导者在确认记录后立即失败，但在追随者复制之前，则记录将丢失。
     * acks=all这意味着引导器将等待完整的同步复制副本来确认记录。这保证了只要至少有一个同步复制副本保持活动状态，记录就不会丢失。这是最有力的保证。这相当于acks=-1的设置。
     */
    private String acks;

}
