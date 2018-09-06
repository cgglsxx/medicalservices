package com.api.dto.clinic;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 门诊缴费预算参数对象
 */
public class ClinicPreAccountHisDto extends ParentDto {
    //病人唯一识别码
    @ToMapAnno(name = "patId")
    private String patId;
    //渠道
    @ToMapAnno(name = "cardNo")
    private String cardNo;
    //身份证
    @ToMapAnno(name = "cardType")
    private String cardType;
    //挂号单号
    @ToMapAnno(name = "regId")
    private String regId;
    //单据列表（或处方号）
    @ToMapAnno(name = "receiptList")
    private String receiptList;
    //费用类型列表
    @ToMapAnno(name = "chargeTypeList")
    private String chargeTypeList;
    //自付金额列表
    @ToMapAnno(name = "personAmtList")
    private String personAmtList;
    //收费窗口代码
    @ToMapAnno(name = "tollwindowNo")
    private String tollwindowNo;
    //是否扣院内账户
    @ToMapAnno(name = "whetherDed")
    private String whetherDed;
    //是否自费结算
    @ToMapAnno(name = "whetherSet")
    private String whetherSet;
    //院内卡号
    @ToMapAnno(name = "hospitalcardNo")
    private String hospitalcardNo;
    //院内支付密码
    @ToMapAnno(name = "password")
    private String password;
    public ClinicPreAccountHisDto(ClinicPreAccountDto dto){
        this.regId = dto.getRegId();
        this.receiptList = dto.getReceiptList();
        this.chargeTypeList = dto.getChargeTypeList();
        this.personAmtList = dto.getPersonAmtList();
        this.tollwindowNo = dto.getTollwindowNo();
        this.whetherDed = dto.getWhetherDed();
        this.whetherSet = dto.getWhetherSet();
        this.hospitalcardNo = dto.getHospitalcardNo();
        this.password = dto.getPassword();
        super.setServiceId("ClinicPreAccount");
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

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getReceiptList() {
        return receiptList;
    }

    public void setReceiptList(String receiptList) {
        this.receiptList = receiptList;
    }

    public String getChargeTypeList() {
        return chargeTypeList;
    }

    public void setChargeTypeList(String chargeTypeList) {
        this.chargeTypeList = chargeTypeList;
    }

    public String getPersonAmtList() {
        return personAmtList;
    }

    public void setPersonAmtList(String personAmtList) {
        this.personAmtList = personAmtList;
    }

    public String getTollwindowNo() {
        return tollwindowNo;
    }

    public void setTollwindowNo(String tollwindowNo) {
        this.tollwindowNo = tollwindowNo;
    }

    public String getWhetherDed() {
        return whetherDed;
    }

    public void setWhetherDed(String whetherDed) {
        this.whetherDed = whetherDed;
    }

    public String getWhetherSet() {
        return whetherSet;
    }

    public void setWhetherSet(String whetherSet) {
        this.whetherSet = whetherSet;
    }

    public String getHospitalcardNo() {
        return hospitalcardNo;
    }

    public void setHospitalcardNo(String hospitalcardNo) {
        this.hospitalcardNo = hospitalcardNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
