package com.api.dto.symptoms;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 症状列表查询参数对象
 */
@ApiModel(value="症状列表查询参数对象",description="症状列表查询参数对象")
public class SymptomsListQueryDto implements Serializable {
    //部位编号
    @ApiModelProperty(name="cate_id",value="部位编号",example="1")
    private String cate_id;
    //症状
    @ApiModelProperty(name="title",value="症状",example="1")
    private String title;

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


}
