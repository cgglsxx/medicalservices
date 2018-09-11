package com.api.dto.inpatient;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;

/**
 * 住院预交金缴纳参数对象
 */
public class InpatientPrePaymentHisDto extends ParentDto {
    //账单号
    @ToMapAnno(name = "interid")
    private String interid;
    //病人姓名
    @ToMapAnno(name = "patientName")
    private String patientName;
    //支付者姓名
    @ToMapAnno(name = "payerName")
    private String payerName;
    //预交款金额
    @ToMapAnno(name = "preAmt")
    private String preAmt;
    //支付方式
    @ToMapAnno(name = "payWay")
    private String payWay;
    //支付渠道
    @ToMapAnno(name = "payChannel")
    private String payChannel;
    //平台支付流水号
    @ToMapAnno(name = "payTradeno")
    private String payTradeno;
    public InpatientPrePaymentHisDto(){
        super.setServiceId("inpPrePayment");
    }
    public String getInterid() {
        return interid;
    }

    public void setInterid(String interid) {
        this.interid = interid;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPreAmt() {
        return preAmt;
    }

    public void setPreAmt(String preAmt) {
        this.preAmt = preAmt;
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
}
