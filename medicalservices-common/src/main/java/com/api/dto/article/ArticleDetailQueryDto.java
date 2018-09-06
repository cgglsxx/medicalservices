package com.api.dto.article;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * 医疗咨讯文章详情查询参数对象
 */
@ApiModel(value="医疗咨讯文章详情查询参数对象",description="医疗咨讯文章详情查询参数对象")
public class ArticleDetailQueryDto implements Serializable {
    //文章id
    @ApiModelProperty(name="article_id",value="文章id",example="1",required = true)
    @NotEmpty(message = "文章id不能为空")
    private String article_id;

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }
}
