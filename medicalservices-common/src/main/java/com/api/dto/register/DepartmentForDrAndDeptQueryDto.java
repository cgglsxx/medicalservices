package com.api.dto.register;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 当班科室和医生所在科室信息查询dto
 */
public class DepartmentForDrAndDeptQueryDto extends ParentDto {

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
