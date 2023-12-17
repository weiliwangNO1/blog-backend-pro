package com.cherry.blog.redis.config;

import com.alibaba.fastjson.JSONObject;
import com.cherry.blog.util.tools.ConstantValue;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Cluster;
import org.apache.commons.lang.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.config.SslProvider;
import org.redisson.config.SubscriptionMode;
import org.redisson.connection.balancer.LoadBalancer;
import org.redisson.connection.balancer.RandomLoadBalancer;
import org.redisson.connection.balancer.WeightedRoundRobinBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * redis服务
 * @author weiliwang
 * @date 2023/12/16
 */
@Slf4j
@Configuration
public class RedisConfig {

    @Value("${redis.usemode}")
    private String useMode;

    @Autowired
    private ClusterRedisMode clusterRedisMode;

    @Autowired
    private StandAloneRedisMode standAloneRedisMode;

    @Bean
    public RedissonClient redissonClient(){
        if(StringUtils.isBlank(useMode)) {
            //默认redis单机模式
            useMode = RedisMode.standAlone.name();
        }
        log.info("init redissonClient. use redisMode={}", useMode);
        if(StringUtils.equalsIgnoreCase(RedisMode.standAlone.name(), useMode)) {
            RedissonClient redissonClient = Redisson.create(supply(this::stansAloneRedis));
            return  redissonClient;
        }else {
            RedissonClient redissonClient = Redisson.create(supply(this::clusterRedis));
            return  redissonClient;
        }
    }

    /**
     * 提供Config配置
     * @param supplier
     * @return
     */
    private <T extends Config> T supply(Supplier<T> supplier) {
        return supplier.get();
    }

    /**
     * 使用redis单机模式
     * @return
     */
    private Config stansAloneRedis() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(standAloneRedisMode.getAddress())
                .setSubscriptionConnectionMinimumIdleSize(standAloneRedisMode.getSubscriptionConnectionMinimumIdleSize())
                .setSubscriptionConnectionPoolSize(standAloneRedisMode.getSubscriptionConnectionPoolSize())
                .setConnectionMinimumIdleSize(standAloneRedisMode.getConnectionMinimumIdleSize())
                .setConnectionPoolSize(standAloneRedisMode.getConnectionPoolSize())
                .setDnsMonitoringInterval(standAloneRedisMode.getDnsMonitoringInterval())
                .setIdleConnectionTimeout(standAloneRedisMode.getIdleConnectionTimeout())
                .setConnectTimeout(standAloneRedisMode.getConnectTimeout())
                .setTimeout(standAloneRedisMode.getTimeout())
                .setRetryAttempts(standAloneRedisMode.getRetryAttempts())
                .setRetryInterval(standAloneRedisMode.getRetryInterval())
                .setDatabase(standAloneRedisMode.getDatabase())
                .setPassword(standAloneRedisMode.getPassword())
                .setSubscriptionsPerConnection(standAloneRedisMode.getSubscriptionsPerConnection())
                .setClientName(standAloneRedisMode.getClientName())
                .setSslEnableEndpointIdentification(standAloneRedisMode.getSslEnableEndpointIdentification())
                .setSslProvider(SslProvider.JDK)
                .setSslTruststore(null)
                .setSslTruststorePassword(standAloneRedisMode.getSslTruststorePassword())
                .setSslKeystore(null)
                .setSslKeystorePassword(standAloneRedisMode.getSslKeystorePassword());
        log.info("use stansAloneRedis");
        //设置redisson编码
        config.setCodec(new JsonJacksonCodec());
        return config;
    }

    /**
     * 使用cluster-redis模式
     * @return
     */
    private Config clusterRedis() {
        List<String> nodeAddresses = Arrays.asList(clusterRedisMode.getNodeAddresses().split("\\;+"));
        Config config = new Config();
        config.useClusterServers()
                .setScanInterval(clusterRedisMode.getScanInterval())
                .setReadMode(ReadMode.SLAVE)
                .setSubscriptionMode(SubscriptionMode.SLAVE)
                .setLoadBalancer(new RandomLoadBalancer())
                .setSubscriptionConnectionMinimumIdleSize(clusterRedisMode.getSubscriptionConnectionMinimumIdleSize())
                .setSubscriptionConnectionPoolSize(clusterRedisMode.getSubscriptionConnectionPoolSize())
                .setSlaveConnectionMinimumIdleSize(clusterRedisMode.getSlaveConnectionMinimumIdleSize())
                .setSlaveConnectionPoolSize(clusterRedisMode.getSlaveConnectionPoolSize())
                .setMasterConnectionMinimumIdleSize(clusterRedisMode.getMasterConnectionMinimumIdleSize())
                .setMasterConnectionPoolSize(clusterRedisMode.getMasterConnectionPoolSize())
                .setIdleConnectionTimeout(clusterRedisMode.getIdleConnectionTimeout())
                .setConnectTimeout(clusterRedisMode.getConnectTimeout())
                .setTimeout(clusterRedisMode.getTimeout())
                .setRetryAttempts(clusterRedisMode.getRetryAttempts())
                .setRetryInterval(clusterRedisMode.getRetryInterval())
                .setPassword(clusterRedisMode.getPassword())
                .setSubscriptionsPerConnection(clusterRedisMode.getSubscriptionsPerConnection())
                .setClientName(clusterRedisMode.getClientName())
                .setSslEnableEndpointIdentification(clusterRedisMode.getSslEnableEndpointIdentification())
                .setSslProvider(null)
                .setSslTruststore(null)
                .setSslTruststorePassword(clusterRedisMode.getSslTruststorePassword())
                .setSslKeystore(null)
                .setSslKeystorePassword(clusterRedisMode.getSslKeystorePassword())
                .setNodeAddresses(nodeAddresses);
        log.info("use clusterRedis");
        //设置redisson编码
        config.setCodec(new JsonJacksonCodec());
        return config;
    }

}
