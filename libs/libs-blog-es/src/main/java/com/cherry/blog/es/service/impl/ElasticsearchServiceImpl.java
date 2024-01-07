package com.cherry.blog.es.service.impl;

import com.cherry.blog.es.config.CommonElasticsearch;
import com.cherry.blog.es.service.ElasticsearchService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * es服务
 * @author weili.wang
 * @date 2024/1/7
 */
@Slf4j
@Service
public class ElasticsearchServiceImpl extends CommonElasticsearch implements ElasticsearchService {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient esClient;

    /**
     * 索引的创建
     * @param index
     * @return
     * @throws IOException
     */
    @Override
    public CreateIndexResponse createIndex(String index) throws IOException {
        log.info("走这里.......................................");
        //1.创建索引的请求
        CreateIndexRequest request = new CreateIndexRequest(index);
        //2客户端执行请求，请求后获得响应
        CreateIndexResponse response = esClient.indices().create(request, RequestOptions.DEFAULT);
        log.info("索引的创建{}", response);
        return response;
    }

}
