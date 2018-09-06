package com.api.dto.article;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 医疗咨讯文章列表查询参数对象
 */
@ApiModel(value="医疗咨讯文章列表查询参数对象",description="医疗咨讯文章列表查询参数对象")
public class ArticleListQueryDto implements Serializable {
    //咨讯类型
    @ApiModelProperty(name="cate_id",value="医疗咨讯分类",example="1")
    private String cate_id;
    //文章标题
    @ApiModelProperty(name="title",value="文章标题",example="1")
    private String title;
    //文章关键字
    @ApiModelProperty(name="keywords",value="文章关键字",example="1")
    private String keywords;

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

}
