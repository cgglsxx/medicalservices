package com.api.dto.clinic;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;
import io.swagger.annotations.ApiModel;

/**
 * 门诊缴费结算参数对象
 */
@ApiModel(value="门诊缴费预算参数对象",description="门诊缴费预算参数对象")
public class ClinicAccountHisDto extends ParentDto {
    //病人唯一识别码
    @ToMapAnno(name = "patId")
    private String patId;
    //渠道
    @ToMapAnno(name = "cardNo")
    private String cardNo;
    //身份证
    @ToMapAnno(name = "cardType")
    private String cardType;
    //结算单据号（收据号）
    @ToMapAnno(name = "statementNo")
    private String statementNo;
    //单据列表（或处方号）
    @ToMapAnno(name = "receiptList")
    private String receiptList;
    //费用类型列表
    @ToMapAnno(name = "chargeTypeList")
    private String chargeTypeList;
    //总金额
    @ToMapAnno(name = "personAmtList")
    private String personAmtList;
    //自付金额列表
    @ToMapAnno(name = "chargeTamt")
    private String chargeTamt;
    //医保支付金额
    @ToMapAnno(name = "payMoney")
    private String payMoney;
    //医院优惠金额
    @ToMapAnno(name = "discountsAmt")
    private String discountsAmt;
    //个人支付金额
    @ToMapAnno(name = "personAmt")
    private String personAmt;
    //支付方式（0-无第三方支付（即个人支付金额为0），1-支付宝，2-微信支付，3-银联卡支付，4-院内支付）
    @ToMapAnno(name = "payWay")
    private String payWay;
    //支付渠道
    @ToMapAnno(name = "payChannel")
    private String payChannel;
    //支付流水号
    @ToMapAnno(name = "payTradeno")
    private String payTradeno;
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
    public ClinicAccountHisDto(){
        super.setServiceId("ClinicAccount");
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

    public String getStatementNo() {
        return statementNo;
    }

    public void setStatementNo(String statementNo) {
        this.statementNo = statementNo;
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

    public String getChargeTamt() {
        return chargeTamt;
    }

    public void setChargeTamt(String chargeTamt) {
        this.chargeTamt = chargeTamt;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getDiscountsAmt() {
        return discountsAmt;
    }

    public void setDiscountsAmt(String discountsAmt) {
        this.discountsAmt = discountsAmt;
    }

    public String getPersonAmt() {
        return personAmt;
    }

    public void setPersonAmt(String personAmt) {
        this.personAmt = personAmt;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getPayTradeno() {
        return payTradeno;
    }

    public void setPayTradeno(String payTradeno) {
        this.payTradeno = payTradeno;
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
