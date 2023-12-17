package com.cherry.blog.redis.config;

import com.cherry.blog.util.tools.ConstantValue;
import com.cherry.blog.util.tools.MagicValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * redis集群模式配置
 * @author weiliwang
 * @date 2023/12/17
 */
@Slf4j
@Getter
@Setter
@Component
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "redis.cluster")
public class ClusterRedisMode {

    /**
     * 添加节点地址
     */
    private String nodeAddresses = ConstantValue.REDIS_STANDALONE_HOST;

    /**
     * 集群扫描间隔时间
     */
    private Integer scanInterval = MagicValue.ONE_THOUSAN;

    /**
     *  用于指定数据分片过程中的分片数量
     */
    private Integer slots = MagicValue.TWO_HUNDRED_AND_THIRTY_ONE;

    /**
     * 读取操作的负载均衡模式  只在从服务节点里读取
     */
    private String readMode = ConstantValue.SLAVE;

    /**
     * 只在从服务节点里订阅
     */
    private String subscriptionMode = ConstantValue.SLAVE;

    /**
     * 负载均衡算法类的选择
     */
    private String loadBalancer = ConstantValue.LOADBALANCER;

    /**
     * 从节点发布和订阅连接的最小空闲连接数
     */
    private Integer subscriptionConnectionMinimumIdleSize = MagicValue.ONE;

    /**
     * 从节点发布和订阅连接池大小
     */
    private Integer subscriptionConnectionPoolSize = MagicValue.FIFTY;

    /**
     * 从节点最小空闲连接数
     */
    private Integer slaveConnectionMinimumIdleSize = MagicValue.THIRTY_TOW;

    /**
     * 从节点连接池大小
     */
    private Integer slaveConnectionPoolSize = MagicValue.SIXTY_FOUR;

    /**
     * 主节点最小空闲连接数
     */
    private Integer masterConnectionMinimumIdleSize = MagicValue.THIRTY_TOW;

    /**
     * 主节点连接池大小
     */
    private Integer masterConnectionPoolSize = MagicValue.SIXTY_FOUR;

    /**
     * 连接空闲超时，单位：毫秒
     */
    private Integer idleConnectionTimeout = MagicValue.TEN_THOUSAND;

    /**
     * 连接超时，单位：毫秒
     */
    private Integer connectTimeout = MagicValue.TEN_THOUSAND;

    /**
     * 命令等待超时，单位：毫秒
     */
    private Integer timeout = MagicValue.THREE_THOUSAND;

    /**
     * 命令失败重试次数
     */
    private Integer retryAttempts = MagicValue.THREE;

    /**
     * 命令重试发送时间间隔，单位：毫秒
     */
    private Integer retryInterval = MagicValue.ONE_THOUSAND_AND_FIVE_HUNDRED;

    /**
     * 密码
     */
    private String password = ConstantValue.REDIS_PASSWORD;

    /**
     * 单个连接最大订阅数量
     */
    private Integer subscriptionsPerConnection = MagicValue.FIVE;

    /**
     * 客户端名称
     */
    private String clientName;

    /**
     * 启用SSL终端识别
     */
    private Boolean sslEnableEndpointIdentification = ConstantValue.TRUE;

    /**
     * SSL实现方式
     */
    private String sslProvider = ConstantValue.JDK;

    /**
     * SSL信任证书库路径
     */
    private String sslTruststore;

    /**
     * SSL信任证书库密码
     */
    private String sslTruststorePassword;

    /**
     * SSL钥匙库路径
     */
    private String sslKeystore;

    /**
     * SSL钥匙库密码
     */
    private String sslKeystorePassword;

}
