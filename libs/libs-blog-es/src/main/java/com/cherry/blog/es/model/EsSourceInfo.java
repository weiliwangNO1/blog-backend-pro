package com.cherry.blog.es.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * 测试
 * @author weili.wang
 * @date 2024/1/6
 */
@Data
@Document(indexName = "my_index")
public class EsSourceInfo  implements Serializable {

    private static final long serialVersionUID = -4780769443664126870L;

    /**
     * 当前字段不能被分词
     */
    @Field(type = FieldType.Keyword)
    private String lngId;

    /**
     * 不加@Field注解，创建索引时会使用默认的Field设置
     */
    private String remark;

    /**
     * analyzer = "ik_smart" 创建索引使用的分词策略
     * type = FieldType.Text 字段类型为文本类型
     * searchAnalyzer = "ik_max_word" 检索时的分词策略
     */
    @Field(analyzer = "ik_smart",type = FieldType.Text,searchAnalyzer = "ik_max_word")
    private String discreption;

    /**
     * analyzer = "ik_max_word" 创建索引使用的分词策略
     * type = FieldType.Text 字段类型为文本类型
     * searchAnalyzer = "ik_smart" 检索时的分词策略
     */
    @Field(analyzer = "ik_max_word",type = FieldType.Text,searchAnalyzer = "ik_smart")
    private String address;

    /**
     * type = FieldType.Keyword字段类型为文本类型
     * 这里的keywordsArrays为后期用来做聚类的字段，不会被分词器分词
     */
    @Field(type = FieldType.Keyword)
    private String[] keywordsArrays;
}
