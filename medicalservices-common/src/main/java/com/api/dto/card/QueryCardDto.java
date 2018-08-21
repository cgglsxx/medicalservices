package com.api.dto.card;


import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * 查询绑卡参数dto
 */
public class QueryCardDto implements Serializable {
    //账号id
    @NotEmpty(message = "第三方账号id不能为空")
    private String out_platform_id;
    //渠道
    @NotEmpty(message = "渠道不能为空")
    private String channel;
    @NotEmpty(message = "机构编号不能为空")
    private String orgCode;

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

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
