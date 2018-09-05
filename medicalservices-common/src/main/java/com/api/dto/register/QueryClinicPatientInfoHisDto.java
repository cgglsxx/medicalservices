package com.api.dto.register;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;

/**
 * 病人门诊候诊信息查询参数对象
 */
public class QueryClinicPatientInfoHisDto extends ParentDto {
    //患者编号
    @ToMapAnno(name = "patId")
    private String patId;
    //卡号
    @ToMapAnno(name = "cardNo")
    private String cardNo;
    //卡类型
    @ToMapAnno(name = "cardType")
    private String cardType;

    public QueryClinicPatientInfoHisDto(){
        super.setServiceId("queryClinicPatientInfo");
    }

    public String getPatId() {
        return patId;
    }

    public void setPatId(String patId) {
        this.patId = patId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }


}
