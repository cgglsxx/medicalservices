package com.api.dto.register;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 门诊可挂号（预约）根据排班类别获取当班科室当班医生信息查询dto
 */
@ApiModel(value="门诊可挂号（预约）根据排班类别获取当班科室当班医生信息查询参数对象",description="门诊可挂号（预约）根据排班类别获取当班科室当班医生信息查询参数对象")
public class ObtainDrSectionQueryDto extends ParentDto {

    //挂号类型
    @ApiModelProperty(name="regType",value="挂号类型(1-专家，2-专病，3-普通，4-特需门诊)",example="1",required =true)
    @NotEmpty(message = "挂号类型不能为空")
    @ToMapAnno(name = "regType")
    private String regType;
    //预约或挂号
    @ApiModelProperty(name="isReg",value="预约或挂号(0-预约，1-挂号)",example="1",required =true)
    @NotEmpty(message = "isReg不能为空")
    @ToMapAnno(name = "isReg")
    private String isReg;
    //开始日期
    @ApiModelProperty(name="startingDate",value="开始日期(yyyy-MM-dd)",example="2018-08-23",required = true)
    @NotEmpty(message = "开始时间不能为空")
    @ToMapAnno(name = "startingDate")
    private String startingDate;
    //结束日期
    @ApiModelProperty(name="closingDate",value="结束日期(yyyy-MM-dd)",example="2018-08-23",required = true)
    @NotEmpty(message = "结束时间不能为空")
    @ToMapAnno(name = "closingDate")
    private String closingDate;

    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }

    public String getIsReg() {
        return isReg;
    }

    public void setIsReg(String isReg) {
        this.isReg = isReg;
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
