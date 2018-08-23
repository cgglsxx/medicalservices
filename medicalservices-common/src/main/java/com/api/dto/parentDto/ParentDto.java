package com.api.dto.parentDto;


import com.api.selfannotation.ToMapAnno;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * 参数dto父类
 */
@ApiModel(value="参数父类",description="参数父类")
public class ParentDto implements Serializable{
    //服务ID
    @ToMapAnno(name = "serviceId")
    @ApiModelProperty(name="serviceId",value="服务ID")
    private String serviceId;
    //机构编码
    @ApiModelProperty(name="orgCode",value="机构编码",example = "429204962")
    @ToMapAnno(name = "orgCode")
    private String orgCode = "429204962";
    //院区编码
    @ApiModelProperty(name="hosId",value="院区编码",example = "B001")
    @ToMapAnno(name = "hosId")
    private String hosId ="B001";
    //院区名称
    @ApiModelProperty(name="hospital",value="院区名称",example = "贵阳人民医院")
    @ToMapAnno(name = "hospital")
    private String hospital = "贵阳人民医院";
    //应用编码
    @ApiModelProperty(name="appCode",value="应用编码",example = "99")
    @ToMapAnno(name = "appCode")
    private String appCode = "99";
    //应用开发商代码
    @ApiModelProperty(name="devCode",value="应用开发商代码",example = "YHYBCS")
    @ToMapAnno(name = "devCode")
    private String devCode = "YHYBCS";
    //调用者身份代码
    @ApiModelProperty(name="operId",value="调用者身份代码",example = "YH001")
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
