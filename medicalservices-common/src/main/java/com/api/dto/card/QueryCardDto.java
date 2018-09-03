package com.api.dto.card;


import com.api.dto.parentDto.ParentDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * 查询绑卡参数dto
 */
@ApiModel(value="查询绑卡参数对象",description="查询绑卡参数对象")
public class QueryCardDto extends ParentDto implements Serializable {
    //账号id
    @ApiModelProperty(name="out_platform_id",value="第三方个人id",example="2088999",required =true)
    @NotEmpty(message = "第三方账号id不能为空")
    private String out_platform_id;
    //渠道
    @ApiModelProperty(name="channel",value="渠道 (10:表示公众号 20:表示生活号)",example="10",required =true)
    @NotEmpty(message = "渠道不能为空")
    private String channel;


    public String getOut_platform_id() {
        return out_platform_id;
    }

    public void setOut_platform_id(String out_platform_id) {
        this.out_platform_id = out_platform_id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
