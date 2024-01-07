package com.cherry.blog.es.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.mapper.ObjectMapper;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * es索引、文档操作服务类
 * @author weili.wang
 * @date 2024/1/7
 */
@Slf4j
@Component
public class CommonElasticsearch extends AbstractBaseElasticsearch<String>{

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
        //1.创建索引的请求
        CreateIndexRequest request = new CreateIndexRequest(index);
        //2客户端执行请求，请求后获得响应
        CreateIndexResponse response = esClient.indices().create(request, RequestOptions.DEFAULT);
        log.info("索引的创建{}", response);
        return response;
    }

    /**
     * 索引是否存在
     * @param index
     * @return
     * @throws IOException
     */
    @Override
    public Boolean existIndex(String index) throws IOException {
        //1.创建索引的请求
        GetIndexRequest request = new GetIndexRequest(index);
        //2客户端执行请求，请求后获得响应
        boolean exist = esClient.indices().exists(request, RequestOptions.DEFAULT);
        log.info("索引是否存在-----" + exist);
        return exist;
    }

    /**
     * 删除索引
     * @param index
     * @return
     * @throws IOException
     */
    @Override
    public Boolean deleteIndex(String index) throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        AcknowledgedResponse delete = esClient.indices().delete(request, RequestOptions.DEFAULT);
        log.info("删除索引--------" + delete.isAcknowledged());
        return delete.isAcknowledged();
    }

    /**
     * 添加文档
     * @param dynamic
     * @param index
     * @return
     * @throws IOException
     */
    @Override
    public IndexResponse addDocument(ObjectMapper.Dynamic dynamic, String index) throws IOException {
        IndexRequest request = new IndexRequest(index);
        //设置超时时间
        request.timeout("1s");
        //将数据放到json字符串
        request.source(JSON.toJSONString(dynamic), XContentType.JSON);
        //发送请求
        IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);
        log.info("添加文档-------" + response.toString());
        log.info("添加文档-------" + response.status());
        return response;
    }

    /**
     * 文档是否存在
     * @param index
     * @param documents
     * @return
     * @throws IOException
     */
    @Override
    public Boolean existDocument(String index, String documents) throws IOException {
        //文档的 没有index
        GetRequest request = new GetRequest(index, documents);
        //没有indices()
        boolean exist = esClient.exists(request, RequestOptions.DEFAULT);
        log.info("文档是否存在-----" + exist);
        return exist;
    }

    /**
     * 获取文档
     * @param index
     * @param documents
     * @return
     * @throws IOException
     */
    @Override
    public GetResponse getDocument(String index, String documents) throws IOException {
        GetRequest request = new GetRequest(index, documents);
        GetResponse response = esClient.get(request, RequestOptions.DEFAULT);
        log.info("获取文档-----" + response.getSourceAsString());
        log.info("获取文档-----" + response);
        return response;
    }

    /**
     * 修改文档
     * @param dynamic
     * @param index
     * @param documents
     * @return
     * @throws IOException
     */
    @Override
    public UpdateResponse updateDocument(ObjectMapper.Dynamic dynamic, String index, String documents) throws IOException {

        //修改是id为1的
        UpdateRequest request = new UpdateRequest(index, documents);
        request.timeout("1s");
        request.doc(JSON.toJSONString(dynamic), XContentType.JSON);

        UpdateResponse response = esClient.update(request, RequestOptions.DEFAULT);
        log.info("修改文档-----" + response);
        log.info("修改文档-----" + response.status());

        return response;
    }


    /**
     * 删除文档
     * @param index
     * @param documents
     * @return
     * @throws IOException
     */
    @Override
    public RestStatus deleteDocument(String index, String documents) throws IOException {
        DeleteRequest request = new DeleteRequest(index, documents);
        request.timeout("1s");
        DeleteResponse response = esClient.delete(request, RequestOptions.DEFAULT);
        log.info("删除文档------" + response.status());
        return response.status();
    }

    /**
     * 批量添加文档
     * @param dynamics
     * @return
     * @throws IOException
     */
    @Override
    public BulkResponse bulkAddDocument(List<ObjectMapper.Dynamic> dynamics) throws IOException {

        //批量操作的Request
        BulkRequest request = new BulkRequest();
        request.timeout("1s");

        //批量处理请求
        for (int i = 0; i < dynamics.size(); i++) {
            request.add(
                    new IndexRequest("lisen_index")
                            .id("" + (i + 1))
                            .source(JSON.toJSONString(dynamics.get(i)), XContentType.JSON)
            );
        }
        BulkResponse response = esClient.bulk(request, RequestOptions.DEFAULT);
        //response.hasFailures()是否是失败的
        log.info("批量添加文档-----" + response.hasFailures());

        //结果:false为成功 true为失败
        return response;
    }


    /**
     * 查询文档
     * @param index
     * @return
     * @throws IOException
     */
    @Override
    public SearchResponse searchDocument(String index) throws IOException {
        SearchRequest request = new SearchRequest(index);
        //构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //设置了高亮
        sourceBuilder.highlighter();
        //term name为cyx1的
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "cyx1");
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        request.source(sourceBuilder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        log.info("查询文档-----" + JSON.toJSONString(response.getHits()));
        log.info("=====================");
        for (SearchHit documentFields : response.getHits().getHits()) {
            log.info("查询文档--遍历参数--" + documentFields.getSourceAsMap());
        }
        return response;
    }

    /**
     * 提供索引添加文档
     * @param dynamic
     * @param index
     * @param id
     * @return
     * @throws IOException
     */
    @Override
    public IndexResponse addDocumentId(ObjectMapper.Dynamic dynamic, String index, String id) throws IOException {
        IndexRequest request = new IndexRequest(index);
        //设置超时时间
        request.id(id);
        //将数据放到json字符串
        request.source(JSON.toJSONString(dynamic), XContentType.JSON);
        //发送请求
        IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);
        log.info("添加文档-------" + response.toString());
        log.info("添加文档-------" + response.status());
        return response;
    }
}
