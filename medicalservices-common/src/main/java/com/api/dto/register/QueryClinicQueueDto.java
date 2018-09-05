package com.api.dto.register;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 门诊候诊信息查询参数对象
 */
@ApiModel(value="门诊候诊信息查询参数对象",description="门诊候诊信息查询参数对象")
public class QueryClinicQueueDto extends ParentDto {
    //账号id
    @ApiModelProperty(name="QueueType",value="排队类型(0 -全部， 1-普通门诊， 2-专家或特需， 3-检查， 4，检验)",example="1",required =true)
    @NotEmpty(message = "账号id不能为空")
    @ToMapAnno(name = "QueueType")
    private String QueueType;

    public String getQueueType() {
        return QueueType;
    }

    public void setQueueType(String queueType) {
        QueueType = queueType;
    }
}
