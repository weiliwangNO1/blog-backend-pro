package com.cherry.blog.kafka.config;

import com.cherry.blog.util.env.BlogEnvironmentConfig;
import com.cherry.blog.util.tools.MagicValue;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.*;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author weili.wang
 * @version 1.0
 * @date 2024/3/9 23:47
 */
@Slf4j
@Component
public class KafkaConfig<K, V> {

    private KafkaProduceConfig kafkaProduceConfig;
    private KafkaConsumerConfig kafkaConsumerConfig;
    private KafkaListenConfig kafkaListenConfig;
    private BlogEnvironmentConfig blogEnvironmentConfig;

    @Autowired
    public KafkaConfig(KafkaProduceConfig kafkaProduceConfig,
                       KafkaConsumerConfig kafkaConsumerConfig,
                       KafkaListenConfig kafkaListenConfig,
                       BlogEnvironmentConfig blogEnvironmentConfig) {
        this.kafkaProduceConfig = kafkaProduceConfig;
        this.kafkaConsumerConfig = kafkaConsumerConfig;
        this.kafkaListenConfig = kafkaListenConfig;
        this.blogEnvironmentConfig = blogEnvironmentConfig;
    }

    /**
     * 生产者-配置转换
     * @param:
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author weili.wang
     * @date 2024-03-10 10:18
     */
    private Map<String, Object> transferProduceProperties() {
        Map<String, Object> props = new HashMap<>(MagicValue.SIXTEEN);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProduceConfig.getBootstrapServers());
        props.put(ProducerConfig.RETRIES_CONFIG, kafkaProduceConfig.getRetries());
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, kafkaProduceConfig.getBatchSize());
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, kafkaProduceConfig.getBufferMemory());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, kafkaProduceConfig.getKeyDeserializer());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, kafkaProduceConfig.getValueDeserializer());
        props.put(ProducerConfig.ACKS_CONFIG, kafkaProduceConfig.getAcks());
        return props;
    }

    /**
     * 消费者-配置转换
     * @param:
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author weili.wang
     * @date 2024-03-10 10:24
     */
    private Map<String, Object> transferConsumerProperties() {
        Map<String, Object> props = new HashMap<>(MagicValue.SIXTEEN);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, kafkaConsumerConfig.getEnableAutoCommit());
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConsumerConfig.getBootstrapServers());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConsumerConfig.getGroupId());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConsumerConfig.getAutoOffsetReset());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, kafkaConsumerConfig.getKeyDeserializer());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, kafkaConsumerConfig.getValueDeserializer());
        return props;
    }

    /**
     * 生产者
     * @param:
     * @return org.springframework.kafka.core.ProducerFactory<java.lang.String,java.lang.String>
     * @author weili.wang
     * @date 2024-03-10 09:56
     */
    @Bean
    private ProducerFactory<String, Object> producerFactory() {
        Supplier<Map<String, Object>> supplier = this::transferProduceProperties;
        return new DefaultKafkaProducerFactory(supplier.get());
    }

    /**
     * 
     * @param: producerFactory
     * @return org.springframework.kafka.transaction.KafkaTransactionManager<java.lang.String,java.lang.Object>
     * @author weili.wang
     * @date 2024-03-10 10:28
     */
    @Bean
    public KafkaTransactionManager<String, Object> kafkaTransactionManager(ProducerFactory<String, Object> producerFactory) {
        return new KafkaTransactionManager(producerFactory);
    }

    /**
     * 
     * @param: 
     * @return org.springframework.kafka.core.KafkaTemplate<java.lang.String,java.lang.Object>
     * @author weili.wang
     * @date 2024-03-10 10:28
     */
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate(producerFactory());
    }

    /**
     * 消费者
     * @param:
     * @return org.springframework.kafka.core.ConsumerFactory<java.lang.String,java.lang.String>
     * @author weili.wang
     * @date 2024-03-10 09:57
     */
    @Bean
    private ConsumerFactory<String, String> consumerFactory() {
        Supplier<Map<String, Object>> supplier = this::transferConsumerProperties;
        return new DefaultKafkaConsumerFactory(supplier.get());
    }


}
