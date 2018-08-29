package com.api.dto.register;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;

/**
 * 门诊预约登记dto
 */
public class RegRegisterDto extends ParentDto {
    //病人唯一码
    @ToMapAnno(name = "patId")
    private String patId;
    //卡号
    @ToMapAnno(name = "cardNo")
    private String cardNo;
    //卡类型
    @ToMapAnno(name = "cardType")
    private String cardType;
    //排班ID
    @ToMapAnno(name = "tscid")
    private String tscid;
    //号源序号
    @ToMapAnno(name = "seqnum")
    private String seqnum;
    //挂号日期
    @ToMapAnno(name = "tscdate")
    private String tscdate;

    public RegRegisterDto(RegOrderSaveDto dto){
        this.tscid = dto.getTscid();
        this.seqnum = dto.getSeqnum();
        this.tscdate = dto.getTscdate();
        super.setServiceId("regRegister");
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

    public String getTscid() {
        return tscid;
    }

    public void setTscid(String tscid) {
        this.tscid = tscid;
    }

    public String getSeqnum() {
        return seqnum;
    }

    public void setSeqnum(String seqnum) {
        this.seqnum = seqnum;
    }

    public String getTscdate() {
        return tscdate;
    }

    public void setTscdate(String tscdate) {
        this.tscdate = tscdate;
    }
}
