package com.cherry.blog.platform.es.controller;

import com.cherry.blog.es.model.article.Article;
import com.cherry.blog.es.repo.ArticleElasticSearchRepo;
import com.cherry.blog.es.service.ElasticsearchService;
import com.cherry.blog.platform.es.model.EsResponseUtils;
import com.cherry.blog.util.enums.ResponseCodeEnum;
import com.cherry.blog.util.tools.ConstantValue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * es接口
 * @author weili.wang
 * @date 2024/1/7
 */
@Api(tags = "elasticSearch操作")
@RestController
@RequestMapping("/elasticSearch")
public class ElasticsearchController {

    @Autowired
    private ElasticsearchService elasticsearchService;

    @Autowired
    private ArticleElasticSearchRepo articleElasticSearchRepo;

    @PostConstruct
    public void init() {
        try {
            if(!elasticsearchService.existIndex("111222")) {
                CreateIndexResponse createIndexResponse = elasticsearchService.createIndex("111222");
                System.out.println(createIndexResponse);
            }
            Article article = Article.builder().id("1")
                    .title("Java")
                    .language("English")
                    .author("weili.wang")
                    .price(1.0F)
                    .description("weili.wang-Java")
                    .build();
            articleElasticSearchRepo.save(article);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建索引
     * @param index
     * @return
     * @throws IOException
     */
    @ApiOperation("索引的创建")
    @PostMapping("/createIndex")
    public EsResponseUtils createIndex(@RequestParam String index) throws IOException {
        CreateIndexResponse createIndexResponse = elasticsearchService.createIndex(index);
        return EsResponseUtils.builder().success(ConstantValue.TRUE)
                .code(ResponseCodeEnum.OK.getCode());
    }

    /**索引是否存在*//*
    @GetMapping("/existIndex")
    @ApiOperation("索引是否存在")
    public ResponseUtils existIndex(@RequestParam String index) throws IOException {
        return ResponseUtils.success(elasticSearchUtil.existIndex(index));
    }

    *//**删除索引*//*
    @DeleteMapping("/deleteIndex")
    @ApiOperation("删除索引")
    public ResponseUtils deleteIndex(@RequestParam String index) throws IOException {
        return ResponseUtils.success(elasticSearchUtil.deleteIndex(index));
    }

    *//**添加文档*//*
    @PostMapping("/addDocument")
    @ApiOperation("添加文档随机id")
    public ResponseUtils addDocument(@RequestBody Dynamic dynamic, @RequestParam String index) throws IOException {
        return ResponseUtils.success(elasticSearchUtil.addDocument(dynamic,index));
    }

    *//**添加文档*//*
    @PostMapping("/addDocument")
    @ApiOperation("添加文档自定义id")
    public ResponseUtils addDocumentId(@RequestBody Dynamic dynamic, @RequestParam String index,@RequestParam String id) throws IOException {
        return ResponseUtils.success(elasticSearchUtil.addDocumentId(dynamic,index,id));
    }

    *//**文档是否存在*//*
    @GetMapping("/existDocument")
    @ApiOperation("文档是否存在")
    public ResponseUtils existDocument(@RequestParam String index, @RequestParam String documents) throws IOException {
        return ResponseUtils.success(elasticSearchUtil.existDocument(index,documents));
    }

    *//**获取文档*//*
    @GetMapping("/getDocument")
    @ApiOperation("获取文档")
    public ResponseUtils getDocument(@RequestParam String index, @RequestParam String documents) throws IOException {
        return ResponseUtils.success(elasticSearchUtil.getDocument(index,documents));
    }

    *//**修改文档*//*
    @ApiOperation("修改文档")
    @PutMapping("/updateDocument")
    public ResponseUtils updateDocument(@RequestBody Dynamic dynamic, @RequestParam String index, @RequestParam String documents) throws IOException {

        return ResponseUtils.success(elasticSearchUtil.updateDocument(dynamic,index,documents));
    }


    *//**删除文档*//*
    @ApiOperation("删除文档")
    @DeleteMapping("/deleteDocument")
    public ResponseUtils deleteDocument(@RequestParam String index, @RequestParam String documents) throws IOException {
        return ResponseUtils.success(elasticSearchUtil.deleteDocument(index,documents));
    }

    *//**批量添加文档*//*
    @ApiOperation("批量添加文档")
    @PostMapping("/bulkAddDocument")
    public ResponseUtils bulkAddDocument(@RequestBody List<Dynamic> dynamics) throws IOException {

        return ResponseUtils.success(elasticSearchUtil.bulkAddDocument(dynamics));
    }


    *//**查询文档*//*
    @ApiOperation("查询文档")
    @GetMapping("/searchDocument")
    public ResponseUtils searchDocument(@RequestParam String index) throws IOException {
        return ResponseUtils.success(elasticSearchUtil.searchDocument(index));
    }*/

}
