package com.api.dto.card;


import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * 查询绑卡参数dto
 */
public class UnBindCardDto implements Serializable {
    //账号id
    @NotEmpty(message = "账号id不能为空")
    private String out_platform_id;
    //渠道
    @NotEmpty(message = "渠道不能为空")
    private String channel;
    @NotEmpty(message = "机构编号不能为空")
    private String orgCode;
    //身份证号
    @NotEmpty(message = "身份证不能为空")
    private String idcard_no;
    //卡类型
    @NotEmpty(message = "卡类型不能为空")
    private String cardType;


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
