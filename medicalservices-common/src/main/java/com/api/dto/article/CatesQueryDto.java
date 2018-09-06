package com.api.dto.article;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * 医疗咨讯分类查询参数对象
 */
@ApiModel(value="医疗咨讯分类查询参数对象",description="医疗咨讯分类查询参数对象")
public class CatesQueryDto implements Serializable {
    //分类类型
    @ApiModelProperty(name="cate_type",value="分类类型( 1.新闻公告  2. 招聘 3. 服务支持 0全部) 该分类根据用户管理系统决定",example="1",required =true)
    @NotEmpty(message = "分类类型不能为空")
    private String cate_type;

    public String getCate_type() {
        return cate_type;
    }

    public void setCate_type(String cate_type) {
        this.cate_type = cate_type;
    }
}
