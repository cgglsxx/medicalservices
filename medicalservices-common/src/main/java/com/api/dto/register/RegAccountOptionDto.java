package com.api.dto.register;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;

/**
 * 挂号结算dto
 */
public class RegAccountOptionDto extends ParentDto {
    //病人唯一码
    @ToMapAnno(name = "patId")
    private String patId;
    //卡号
    @ToMapAnno(name = "cardNo")
    private String cardNo;
    //卡类型
    @ToMapAnno(name = "cardType")
    private String cardType;
    //预约挂号标志
    @ToMapAnno(name = "preregFlag")
    private String preregFlag;
    //挂号类别
    @ToMapAnno(name = "regType")
    private String regType;
    //科室代码
    @ToMapAnno(name = "deptId")
    private String deptId;
    //医生代码
    @ToMapAnno(name = "drId")
    private String drId;
    //排班ID
    @ToMapAnno(name = "tscid")
    private String tscid;
    //号源序号
    @ToMapAnno(name = "seqnum")
    private String seqnum;
    //挂号日期
    @ToMapAnno(name = "tscdate")
    private String tscdate;
    //上下午标志
    @ToMapAnno(name = "daySection")
    private String daySection;
    //挂号单号
    @ToMapAnno(name = "regId")
    private String regId;
    //挂号费是否托收 （0-挂号时收取 1-缴费时收取（preregFlag=1时必须为 1））
    @ToMapAnno(name = "delayPay")
    private String delayPay;
    //挂号总金额
    @ToMapAnno(name = "regAmt")
    private String regAmt;
    //自付金额
    @ToMapAnno(name = "personAmt")
    private String personAmt;
    //医保支付金额
    @ToMapAnno(name = "payMoney")
    private String payMoney;
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
    //收据号
    @ToMapAnno(name = "receiptNo")
    private String receiptNo;

    public RegAccountOptionDto(){
        super.setServiceId("regAccount");
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

    public String getPreregFlag() {
        return preregFlag;
    }

    public void setPreregFlag(String preregFlag) {
        this.preregFlag = preregFlag;
    }

    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
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

    public String getDaySection() {
        return daySection;
    }

    public void setDaySection(String daySection) {
        this.daySection = daySection;
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

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getDelayPay() {
        return delayPay;
    }

    public void setDelayPay(String delayPay) {
        this.delayPay = delayPay;
    }

    public String getRegAmt() {
        return regAmt;
    }

    public void setRegAmt(String regAmt) {
        this.regAmt = regAmt;
    }

    public String getPersonAmt() {
        return personAmt;
    }

    public void setPersonAmt(String personAmt) {
        this.personAmt = personAmt;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
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

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }
}
