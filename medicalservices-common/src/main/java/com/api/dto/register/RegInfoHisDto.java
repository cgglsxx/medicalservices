package com.api.dto.register;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 门诊已预约、挂号信息查询参数对象
 */
public class RegInfoHisDto extends ParentDto {
    //患者编号
    @ToMapAnno(name = "patId")
    private String patId;
    //卡号
    @ToMapAnno(name = "cardNo")
    private String cardNo;
    //卡类型
    @ToMapAnno(name = "cardType")
    private String cardType;
    //开始日期
    @ToMapAnno(name = "startingDate")
    private String startingDate;
    //结束日期
    @ToMapAnno(name = "closingDate")
    private String closingDate;
    //预约或挂号
    @ToMapAnno(name = "isReg")
    private String isReg;

    public RegInfoHisDto(RegInfoDto dto){
        this.startingDate = dto.getStartingDate();
        this.closingDate = dto.getClosingDate();
        this.isReg = dto.getIsReg();
        super.setServiceId("queryRegisterInformation");
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

    public String getIsReg() {
        return isReg;
    }

    public void setIsReg(String isReg) {
        this.isReg = isReg;
    }
}
