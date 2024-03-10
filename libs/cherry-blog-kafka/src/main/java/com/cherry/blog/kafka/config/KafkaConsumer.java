package com.cherry.blog.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author weili.wang
 * @version 1.0
 * @date 2024/3/10 9:17
 */
@Slf4j
@Component
public class KafkaConsumer {

    /**
     * kafka的监听器1，topic为"topic_test"，消费者组为"group_topic_test"
     * @param record
     * @param item
     */
    @KafkaListener(topics = "topic_test", groupId = "group_topic_test")
    public void topicListener1(ConsumerRecord<String, String> record, Acknowledgment item) {
        String value = record.value();
        System.out.println(value);
        System.out.println(record);
        //手动提交
        item.acknowledge();
    }

    /**
     * 配置多个消费组
     * kafka的监听器2，topic为"topic_test2"，消费者组为"group_topic_test"
     * @param record
     * @param item
     */
    @KafkaListener(topics = "topic_test2",groupId = "group_topic_test2")
    public void topicListener2(ConsumerRecord<String, String> record, Acknowledgment item) {
        String value = record.value();
        System.out.println(value);
        System.out.println(record);
        item.acknowledge();
    }


}
