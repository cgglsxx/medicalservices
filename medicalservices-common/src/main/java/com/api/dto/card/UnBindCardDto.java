package com.api.dto.card;


import com.api.dto.parentDto.ParentDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * 解绑参数对象
 */
@ApiModel(value="解绑参数对象",description="解绑参数对象")
public class UnBindCardDto extends ParentDto implements Serializable {
    //账号id
    @ApiModelProperty(name="out_platform_id",value="第三方个人id",example="2088999",required =true)
    @NotEmpty(message = "账号id不能为空")
    private String out_platform_id;
    //渠道
    @ApiModelProperty(name="channel",value="渠道 (10:表示公众号 20:表示生活号)",example="10",required =true)
    @NotEmpty(message = "渠道不能为空")
    private String channel;
    //身份证号
    @ApiModelProperty(name="idcard_no",value="身份证号",example="513029199008170677",required = true)
    @NotEmpty(message = "身份证不能为空")
    private String idcard_no;
    //卡类型
    @ApiModelProperty(name="cardType",value="卡类型 (1-就诊卡，2-医保卡，3-居民健康卡，4-身份证，5-病人唯一识别码（病历号、门诊号等）)",example="4",required = true)
    @NotEmpty(message = "卡类型不能为空")
    private String cardType;



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

    public String getIdcard_no() {
        return idcard_no;
    }

    public void setIdcard_no(String idcard_no) {
        this.idcard_no = idcard_no;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

}
