package com.api.dto.register;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;
import com.api.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 查询当日门诊可挂号号源信息dto
 */
@ApiModel(value="当日门诊可挂号号源信息参数对象",description="当日门诊可挂号号源信息参数对象")
public class RegSourceQueryDto extends ParentDto {

    //排班ID
    @ApiModelProperty(name="tscid",value="排班唯一号",example="101",required =true)
    @NotEmpty(message = "排班唯一号")
    @ToMapAnno(name = "tscid")
    private String tscid;
    //挂号日期
    @ApiModelProperty(name="tscdate",value="挂号日期(前段不用此参数后端自动封装当日日期)",example="2018-08-24")
    @ToMapAnno(name = "tscdate")
    private String tscdate = DateUtil.formatDateToString(DateUtil.FORMAT_DEFAULT);
    //科室代码
    @ApiModelProperty(name="deptId",value="科室代码(科室编号或ID，无特殊字符)",example="9901",required = true)
    @NotEmpty(message = "科室代码")
    @ToMapAnno(name = "deptId")
    private String deptId;
    //挂号类型
    @ApiModelProperty(name="regType",value="挂号类型(1-专家，2-专病，3-普通，4-特需门诊)",example="1",required = true)
    @NotEmpty(message = "挂号类型不能为空")
    @ToMapAnno(name = "regType")
    private String regType;
    //医生代码
    @ApiModelProperty(name="drId",value="医生代码",example="1001")
    @ToMapAnno(name = "drId")
    private String drId;

    public String getTscid() {
        return tscid;
    }

    public void setTscid(String tscid) {
        this.tscid = tscid;
    }

    public String getTscdate() {
        return tscdate;
    }

    public void setTscdate(String tscdate) {
        this.tscdate = tscdate;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }

    public String getDrId() {
        return drId;
    }

    public void setDrId(String drId) {
        this.drId = drId;
    }
}
