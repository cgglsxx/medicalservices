package com.api.dto.symptoms;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * 身体部位查询参数对象
 */
@ApiModel(value="身体部位查询参数对象",description="身体部位查询参数对象")
public class BodysQueryDto implements Serializable {
    //父级编号
    @ApiModelProperty(name="parent_id",value="父级编号 0表示一级部位",example="1",required = true)
    @NotEmpty(message = "父级编号不能为空")
    private String parent_id;

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

}
