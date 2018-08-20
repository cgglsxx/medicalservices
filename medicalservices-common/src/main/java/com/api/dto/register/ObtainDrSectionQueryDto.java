package com.api.dto.register;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 门诊可挂号（预约）根据排班类别获取当班科室当班医生信息查询dto
 */
public class ObtainDrSectionQueryDto extends ParentDto {

    //挂号类型
    @NotEmpty(message = "挂号类型不能为空")
    @ToMapAnno(name = "regType")
    private String regType;
    //预约或挂号
    @NotEmpty(message = "isReg不能为空")
    @ToMapAnno(name = "isReg")
    private String isReg;
    //开始日期
    @NotEmpty(message = "开始时间不能为空")
    @ToMapAnno(name = "startingDate")
    private String startingDate;
    //结束日期
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
