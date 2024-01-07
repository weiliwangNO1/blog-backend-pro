package com.cherry.blog.es.repo;

import com.cherry.blog.es.model.article.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * artilce repo
 * @author weili.wang
 * @date 2024/1/7
 */
@Repository
public interface ArticleElasticSearchRepo extends ElasticsearchRepository<Article, String> {
}
