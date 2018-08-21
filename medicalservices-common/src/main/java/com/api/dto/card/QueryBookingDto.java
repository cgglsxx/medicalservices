package com.api.dto.card;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 查询病人基本信息参数dto
 */
public class QueryBookingDto extends ParentDto {
    //身份证号
    @ToMapAnno(name = "certId")
    private String idcard_no;
    //监护人身份证号
    @ToMapAnno(name = "guardianId")
    private String guardianId;
    //姓名
    @ToMapAnno(name = "patientName")
    private String patientName;
    @ToMapAnno(name = "phone")
    private String phone;
    public QueryBookingDto(BindCardDto dto){
        this.idcard_no = dto.getIdcard_no();
        this.guardianId = dto.getGuardianId();
        this.patientName = dto.getPat_name();
        this.phone = dto.getMobile();
        super.setServiceId("queryPatientInfo");
    }
    public QueryBookingDto(BookingDto dto){
        this.idcard_no = dto.getIdcard_no();
        this.guardianId = dto.getGuardianId();
        this.patientName = dto.getPat_name();
        this.phone = dto.getMobile();
        super.setServiceId("queryPatientInfo");
    }
    public String getIdcard_no() {
        return idcard_no;
    }

    public void setIdcard_no(String idcard_no) {
        this.idcard_no = idcard_no;
    }

    public String getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(String guardianId) {
        this.guardianId = guardianId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
