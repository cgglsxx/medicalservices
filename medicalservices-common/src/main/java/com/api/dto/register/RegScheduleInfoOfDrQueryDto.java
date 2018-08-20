package com.api.dto.register;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 查询当班医生号源信息参数dto
 */
public class RegScheduleInfoOfDrQueryDto extends ParentDto {

    //预约或挂号
    @NotEmpty(message = "isReg不能为空")
    @ToMapAnno(name = "isReg")
    private String isReg;
    //科室代码
    @NotEmpty(message = "科室代码不能为空")
    @ToMapAnno(name = "deptId")
    private String deptId;
    //医生代码
    @NotEmpty(message = "医生代码不能为空")
    @ToMapAnno(name = "drId")
    private String drId;
    //开始日期
    @ToMapAnno(name = "startingDate")
    private String startingDate;
    //结束日期
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

    public String getDrId() {
        return drId;
    }

    public void setDrId(String drId) {
        this.drId = drId;
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
