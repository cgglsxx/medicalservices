package com.api.dto.register;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 查询当班医生信息参数dto
 */
@ApiModel(value="查询当班医生信息参数对象",description="查询当班医生信息参数对象")
public class DoctorsQueryDto extends ParentDto {
    //预约或挂号
    @ApiModelProperty(name="isReg",value="预约或挂号(0-预约，1-挂号)",example="1",required =true)
    @NotEmpty(message = "isReg不能为空")
    @ToMapAnno(name = "isReg")
    private String isReg;
    //科室代码
    @ApiModelProperty(name="deptId",value="科室代码(科室编号或ID，无特殊字符)",example="1078",required =true)
    @NotEmpty(message = "科室代码不能为空")
    @ToMapAnno(name = "deptId")
    private String deptId;
    //开始日期
    @ApiModelProperty(name="startingDate",value="开始日期(yyyy-MM-dd，挂号时可为空)",example="2018-08-23")
    @ToMapAnno(name = "startingDate")
    private String startingDate;
    //结束日期
    @ApiModelProperty(name="closingDate",value="结束日期(yyyy-MM-dd，挂号时可为空)",example="2018-08-23")
    @ToMapAnno(name = "closingDate")
    private String closingDate;

    public String getIsReg() {
        return isReg;
    }

    public void setIsReg(String isReg) {
        this.isReg = isReg;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }
}
