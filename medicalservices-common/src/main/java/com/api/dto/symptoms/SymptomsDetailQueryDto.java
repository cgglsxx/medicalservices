package com.api.dto.symptoms;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * 症状详情查询参数对象
 */
@ApiModel(value="症状详情查询参数对象",description="症状详情查询参数对象")
public class SymptomsDetailQueryDto implements Serializable {
    //症状id
    @ApiModelProperty(name="symptoms_id",value="症状id",example="1",required = true)
    @NotEmpty(message = "症状id不能为空")
    private String symptoms_id;

    public String getSymptoms_id() {
        return symptoms_id;
    }

    public void setSymptoms_id(String symptoms_id) {
        this.symptoms_id = symptoms_id;
    }
}
