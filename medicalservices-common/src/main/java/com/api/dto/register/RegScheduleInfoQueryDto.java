package com.api.dto.register;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;

/**
 * 查询医生信息参数dto
 */
public class RegScheduleInfoQueryDto extends ParentDto {

    //科室代码
    @ToMapAnno(name = "ksCode")
    private String ksCode;
    //医生代码
    @ToMapAnno(name = "ksDoctorCode")
    private String ksDoctorCode;
    //开始时间
    @ToMapAnno(name = "beginDate")
    private String beginDate;
    //结束时间
    @ToMapAnno(name = "endDate")
    private String endDate;

    public String getKsCode() {
        return ksCode;
    }

    public void setKsCode(String ksCode) {
        this.ksCode = ksCode;
    }

    public String getKsDoctorCode() {
        return ksDoctorCode;
    }

    public void setKsDoctorCode(String ksDoctorCode) {
        this.ksDoctorCode = ksDoctorCode;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
