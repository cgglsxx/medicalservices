package com.api.dto.inpatient;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;

/**
 * 住院预交款记录查询参数对象
 */
public class InpatientPrePaymentQueryHisDto extends ParentDto {
    //账单号
    @ToMapAnno(name = "interid")
    private String interid;
    //支付方式
    @ToMapAnno(name = "payWay")
    private String payWay;
    public InpatientPrePaymentQueryHisDto(InpatientPrePaymentQueryDto dto){
        this.interid = dto.getInterid();
        this.payWay = dto.getPayWay();
        super.setServiceId("queryPrePayment");
    }
    public String getInterid() {
        return interid;
    }

    public void setInterid(String interid) {
        this.interid = interid;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }
}
