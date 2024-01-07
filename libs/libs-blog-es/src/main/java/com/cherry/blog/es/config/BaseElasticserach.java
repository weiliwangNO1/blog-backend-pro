package com.cherry.blog.es.config;

import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.index.mapper.ObjectMapper;
import org.elasticsearch.rest.RestStatus;

import java.io.IOException;
import java.util.List;

/**
 * es 索引、文档操作
 * @author weili.wang
 * @date 2024/1/7
 */
public interface BaseElasticserach<T> {

    /**
     * 创建索引
     * @param index
     * @return
     * @throws IOException
     */
    public CreateIndexResponse createIndex(T index) throws IOException;

    /**
     * 是否存在索引
     * @param index
     * @return
     * @throws IOException
     */
    public Boolean existIndex(T index) throws IOException;

    /**
     * 删除索引
     * @param index
     * @return
     * @throws IOException
     */
    public Boolean deleteIndex(T index) throws IOException;

    /**
     * 添加文档
     * @param dynamic
     * @param index
     * @return
     * @throws IOException
     */
    public IndexResponse addDocument(ObjectMapper.Dynamic dynamic, T index) throws IOException;

    /**
     * 文档是否存在
     * @param index
     * @param documents
     * @return
     * @throws IOException
     */
    public Boolean existDocument(T index, String documents) throws IOException;

    /**
     * 获取文档
     * @param index
     * @param documents
     * @return
     * @throws IOException
     */
    public GetResponse getDocument(String index, String documents) throws IOException;

    /**
     * 修改文档
     * @param dynamic
     * @param index
     * @param documents
     * @return
     * @throws IOException
     */
    public UpdateResponse updateDocument(ObjectMapper.Dynamic dynamic, T index, String documents) throws IOException;


    /**
     * 删除文档
     * @param index
     * @param documents
     * @return
     * @throws IOException
     */
    public RestStatus deleteDocument(T index, String documents) throws IOException;

    /**
     * 批量添加文档
     * @param dynamics
     * @return
     * @throws IOException
     */
    public BulkResponse bulkAddDocument(List<ObjectMapper.Dynamic> dynamics) throws IOException;

    /**
     * 查询文档
     * @param index
     * @return
     * @throws IOException
     */
    public SearchResponse searchDocument(T index) throws IOException;

    /**
     * 提供索引添加文档
     * @param dynamic
     * @param index
     * @param id
     * @return
     * @throws IOException
     */
    public IndexResponse addDocumentId(ObjectMapper.Dynamic dynamic, String index, String id) throws IOException;
}
