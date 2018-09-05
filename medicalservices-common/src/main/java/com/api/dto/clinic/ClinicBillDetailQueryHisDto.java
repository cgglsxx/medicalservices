package com.api.dto.clinic;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;

/**
 * 门诊缴费单明细查询参数对象
 */
public class ClinicBillDetailQueryHisDto extends ParentDto {
    //患者编号
    @ToMapAnno(name = "patId")
    private String patId;
    //卡号
    @ToMapAnno(name = "cardNo")
    private String cardNo;
    //卡类型
    @ToMapAnno(name = "cardType")
    private String cardType;
    //费用单据号
    @ToMapAnno(name = "receiptNo")
    private String receiptNo;


    public ClinicBillDetailQueryHisDto(ClinicBillDetailQueryDto dto){
        this.receiptNo = dto.getReceiptNo();
        super.setServiceId("queryClinicBillDetail");
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

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }
}
