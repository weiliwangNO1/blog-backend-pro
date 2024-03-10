package com.cherry.blog.kafka.enums;

/**
 * @author weili.wang
 * @version 1.0
 * @date 2024/3/10 10:00
 */
public enum KafkaFieldEnums {

    PRODUCE_BOOTSTRAP_SERVERS("bootstrap-servers"),
    PRODUCE_RETRIES("retries"),
    PRODUCE_BATCH_SIZE("batch-size"),
    PRODUCE_BUFFER_MEMORY("buffer-memory"),
    PRODUCE_KEY_DESERIALIZER("key-deserializer"),
    PRODUCE_VALUE_DESERIALIZER("value-deserializer"),
    PRODUCE_ACKS("acks"),

    CONSUMER_ENABLE_AUTO_COMMIT("enable-auto-commit"),
    CONSUMER_BOOTSTRAP_SERVERS("bootstrap-servers"),
    CONSUMER_GROUP_ID("group-id"),
    CONSUMER_AUTO_OFFSET_RESET("auto-offset-reset"),
    CONSUMER_KEY_DESERIALIZER("key-deserializer"),
    CONSUMER_VALUE_DESERIALIZER("value-deserializer"),

    TOPIC("topic"),

    LISTENER_CONCURRENCY("concurrency"),
    LISTENER_ACK_MODE("ack-mode"),
    LISTENER_MISSING_TOPICES_FATAL("missing-topics-fatal"),
    LISTENER_TYPE("type");

    private String kafkaKey;

    KafkaFieldEnums(String kafkaKey) {
        this.kafkaKey = kafkaKey;
    }

    public String getKafkaKey() {
        return this.kafkaKey;
    }
}
