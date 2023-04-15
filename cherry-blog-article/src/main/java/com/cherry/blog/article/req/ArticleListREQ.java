package com.cherry.blog.article.req;

import com.cherry.blog.entities.Article;
import com.cherry.blog.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 文章列表查询条件
 * @since  2022-1-22
 * @author wwl
 */
@Data
@Accessors(chain = true)
@ApiModel(value="ArticleListREQ对象", description="文章列表查询条件")
public class ArticleListREQ extends BaseRequest<Article> {

    @ApiModelProperty(value = "标签ID")
    private String labelId;

    @ApiModelProperty(value = "分类ID")
    private String categoryId;
}

