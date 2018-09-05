package com.api.dto.clinic;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;

/**
 * 门诊已预约、挂号信息查询参数对象
 */
public class ClinicBillInfoQueryHisDto extends ParentDto {
    //患者编号
    @ToMapAnno(name = "patId")
    private String patId;
    //卡号
    @ToMapAnno(name = "cardNo")
    private String cardNo;
    //卡类型
    @ToMapAnno(name = "cardType")
    private String cardType;
    //查询类别
    @ToMapAnno(name = "queryType")
    private String queryType;
    //开始日期
    @ToMapAnno(name = "startingDate")
    private String startingDate;
    //结束日期
    @ToMapAnno(name = "closingDate")
    private String closingDate;

    public ClinicBillInfoQueryHisDto(ClinicBillInfoQueryDto dto){
        this.queryType = dto.getQueryType();
        this.startingDate = dto.getStartingDate();
        this.closingDate = dto.getClosingDate();
        super.setServiceId("queryClinicBillInfo");
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

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }
}
