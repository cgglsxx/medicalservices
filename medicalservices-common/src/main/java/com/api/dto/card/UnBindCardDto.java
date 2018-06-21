package com.api.dto.card;


import com.api.dto.parentDto.ParentDto;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 查询绑卡参数dto
 */
public class UnBindCardDto extends ParentDto {
    //账号id
    @NotEmpty(message = "账号id不能为空")
    private String yad901;
    //渠道
    @NotEmpty(message = "渠道不能为空")
    private String yad961;
    //身份证号
    @NotEmpty(message = "身份证不能为空")
    private String aac002;
    //卡类型
    @NotEmpty(message = "卡类型不能为空")
    private String cardType;
    //卡号
    @NotEmpty(message = "卡号不能为空")
    private String yac005;

    public String getYad901() {
        return yad901;
    }

    public void setYad901(String yad901) {
        this.yad901 = yad901;
    }

    public String getYad961() {
        return yad961;
    }

    public void setYad961(String yad961) {
        this.yad961 = yad961;
    }

    public String getAac002() {
        return aac002;
    }

    public void setAac002(String aac002) {
        this.aac002 = aac002;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getYac005() {
        return yac005;
    }

    public void setYac005(String yac005) {
        this.yac005 = yac005;
    }
}
