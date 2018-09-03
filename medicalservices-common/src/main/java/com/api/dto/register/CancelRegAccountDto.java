package com.api.dto.register;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;

/**
 * 门诊已预约、挂号信息查询参数对象
 */
public class CancelRegAccountDto extends ParentDto {
    //患者编号
    @ToMapAnno(name = "patId")
    private String patId;
    //卡号
    @ToMapAnno(name = "cardNo")
    private String cardNo;
    //卡类型
    @ToMapAnno(name = "cardType")
    private String cardType;
    //挂号单号
    @ToMapAnno(name = "regId")
    private String regId;
    //挂号收据号
    @ToMapAnno(name = "regReceipt")
    private String regReceipt;
    //退费金额
    @ToMapAnno(name = "refundMoney")
    private String refundMoney;
    //原路退款方式
    @ToMapAnno(name = "payWay")
    private String payWay;
    //支付流水号
    @ToMapAnno(name = "payTradeno")
    private String payTradeno;
    //退费流水号
    @ToMapAnno(name = "refundTradeno")
    private String refundTradeno;
    //备注
    @ToMapAnno(name = "remark")
    private String remark;

    public CancelRegAccountDto(){
        super.setServiceId("CancelRegAccount");
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

    public String getRegReceipt() {
        return regReceipt;
    }

    public void setRegReceipt(String regReceipt) {
        this.regReceipt = regReceipt;
    }

    public String getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(String refundMoney) {
        this.refundMoney = refundMoney;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getPayTradeno() {
        return payTradeno;
    }

    public void setPayTradeno(String payTradeno) {
        this.payTradeno = payTradeno;
    }

    public String getRefundTradeno() {
        return refundTradeno;
    }

    public void setRefundTradeno(String refundTradeno) {
        this.refundTradeno = refundTradeno;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
