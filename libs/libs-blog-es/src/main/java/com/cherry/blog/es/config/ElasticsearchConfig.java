package com.cherry.blog.es.config;

import com.cherry.blog.util.tools.ConstantValue;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * eslasticsearch配置
 * @author weili.wang
 * @date 2024/1/6
 */
@Slf4j
@Configuration
public class ElasticsearchConfig {

    @Value("${elasticsearch.usemode}")
    private String useMode = ConstantValue.ELASTICSEARCH_USE_DEFAULT_MODE;

    @Autowired
    private ClusterEsMode clusterEsMode;

    @Autowired
    private StandAloneEsMode standAloneEsMode;

    /**
     *
     * @return
     */
    @Bean
    @Qualifier("highLevelClient")
    public RestHighLevelClient restHighLevelClient() {
        if(StringUtils.equalsIgnoreCase(ConstantValue.ELASTICSEARCH_USE_DEFAULT_MODE, useMode)) {
            //单节点模式
            RestHighLevelClient highLevelClient = new RestHighLevelClient(
                    RestClient.builder(new HttpHost(standAloneEsMode.getHost(), standAloneEsMode.getPort(), standAloneEsMode.getScheme()))
                            .setRequestConfigCallback(requestConfigBuilder -> {
                                return requestConfigBuilder.setConnectTimeout(standAloneEsMode.getConnectTimeout())
                                        .setSocketTimeout(standAloneEsMode.getSocketTimeout());
                            }));
            return highLevelClient;
        }else {
            //集群模式
            RestHighLevelClient highLevelClient = new RestHighLevelClient(
                    RestClient.builder(new HttpHost(clusterEsMode.getHost(), clusterEsMode.getPort(), clusterEsMode.getScheme()))
                            .setRequestConfigCallback(requestConfigBuilder -> {
                                return requestConfigBuilder.setConnectTimeout(clusterEsMode.getConnectTimeout())
                                        .setSocketTimeout(clusterEsMode.getSocketTimeout());
                            }));
            return highLevelClient;
        }
    }
}
