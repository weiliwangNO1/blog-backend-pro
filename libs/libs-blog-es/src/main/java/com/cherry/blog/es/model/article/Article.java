package com.cherry.blog.es.model.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * article
 * @author weili.wang
 * @date 2024/1/7
 */
@Data
@Builder
@Document(indexName = "article",shards = 1,replicas = 0)
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Keyword)
    private String language;

    @Field(type = FieldType.Keyword)
    private String author;

    @Field(type = FieldType.Float)
    private Float price;

    @Field(type = FieldType.Text)
    private String description;
}


