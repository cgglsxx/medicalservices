package com.api.dto.parentDto;


import com.api.selfannotation.ToMapAnno;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * 参数dto父类
 */
public class ParentDto implements Serializable{
    //服务ID
    @ToMapAnno(name = "serviceId")
    private String serviceId;
    //机构编码
    @NotEmpty(message = "机构编码不能为空")
    @ToMapAnno(name = "orgCode")
    private String orgCode = "429204962";
    //院区编码
    @ToMapAnno(name = "hosId")
    private String hosId ="B001";
    //院区名称
    @ToMapAnno(name = "hospital")
    private String hospital = "贵阳人民医院";
    //应用编码
    @NotEmpty(message = "应用编码不能为空")
    @ToMapAnno(name = "appCode")
    private String appCode = "99";
    //应用开发商代码
    @NotEmpty(message = "应用开发商代码不能为空")
    @ToMapAnno(name = "devCode")
    private String devCode = "YHYBCS";
    //调用者身份代码
    @ToMapAnno(name = "operId")
    private String operId ="YH001";

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getHosId() {
        return hosId;
    }

    public void setHosId(String hosId) {
        this.hosId = hosId;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getDevCode() {
        return devCode;
    }

    public void setDevCode(String devCode) {
        this.devCode = devCode;
    }

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
    }
}
