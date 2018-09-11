package com.api.dto.card;


import com.api.dto.register.RegOrderSaveDto;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * 查询患者是否绑卡
 */
public class QueryCardForPersonDto implements Serializable {
    //账号id
    @NotEmpty(message = "账号id不能为空")
    private String out_platform_id;
    //渠道
    @NotEmpty(message = "渠道不能为空")
    private String channel;
    //机构编号
    @NotEmpty(message = "机构编号不能为空")
    private String orgCode;
    //身份证号
    @NotEmpty(message = "身份证不能为空")
    private String idcard_no;

    public QueryCardForPersonDto(RegOrderSaveDto dto){
        this.out_platform_id = dto.getOut_platform_id();
        this.channel = dto.getChannel();
        this.orgCode = dto.getOrgCode();
        this.idcard_no = dto.getIdcard_no();
    }

    public QueryCardForPersonDto(){

    }
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



}
