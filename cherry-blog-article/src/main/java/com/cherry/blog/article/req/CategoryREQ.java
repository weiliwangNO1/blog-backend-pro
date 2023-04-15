package com.cherry.blog.article.req;

import com.cherry.blog.entities.Category;
import com.cherry.blog.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 文章类别分页查询条件
 */
@ApiModel(value = "CategoryREQ对象", description = "类别查询条件")
@Data
@Accessors(chain = true)
public class CategoryREQ extends BaseRequest<Category> {

    //分类名称
    @ApiModelProperty(value = "分类名称")
    private String name;

    //状态1.正常，0.禁用
    @ApiModelProperty(value = "状态1.正常，0.禁用")
    private Integer status;

}
