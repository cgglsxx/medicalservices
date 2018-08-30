package com.api.dto.register;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;

/**
 * 取消预约登记dto
 */
public class CancelRegisterDto extends ParentDto {
    //病人唯一码
    @ToMapAnno(name = "patId")
    private String patId;
    //卡号
    @ToMapAnno(name = "cardNo")
    private String cardNo;
    //卡类型
    @ToMapAnno(name = "cardType")
    private String cardType;
    //预约登记号
    @ToMapAnno(name = "yydjh")
    private String yydjh;

    public CancelRegisterDto(){
        super.setServiceId("CancelRegister");
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

    public String getYydjh() {
        return yydjh;
    }

    public void setYydjh(String yydjh) {
        this.yydjh = yydjh;
    }
}
