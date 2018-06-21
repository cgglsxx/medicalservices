package com.api.dto.register;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;

/**
 * 查询医生信息参数dto
 */
public class DoctorsQueryDto extends ParentDto {
    //科室代码
    @ToMapAnno(name = "ksCode")
    private String ksCode;
    //医生代码
    @ToMapAnno(name = "ksDoctorCode")
    private String ksDoctorCode;


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

}
