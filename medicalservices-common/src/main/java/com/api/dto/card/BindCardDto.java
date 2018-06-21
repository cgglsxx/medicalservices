package com.api.dto.card;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 绑卡参数dto
 */
public class BindCardDto extends ParentDto {
    //账号id
    @NotEmpty(message = "账号id不能为空")
    private String yad901;
    //渠道
    @NotEmpty(message = "渠道不能为空")
    private String yad961;
    //身份证号
    @NotEmpty(message = "身份证号不能为空")
    @ToMapAnno(name = "aac002")
    private String aac002;
    //姓名
    @NotEmpty(message = "姓名不能为空")
    @ToMapAnno(name = "aac003")
    private String aac003;
    //电话
    @NotEmpty(message = "电话不能为空")
    @ToMapAnno(name = "yae098")
    private String yae098;
    //地址
    private String address;
    //性别
    @NotEmpty(message = "性别不能为空")
    @ToMapAnno(name = "aac004")
    private String aac004;
    //出生日期
    @NotEmpty(message = "出生日期不能为空")
    @ToMapAnno(name = "aac006")
    private String aac006;
    //卡类型
    @NotEmpty(message = "卡类型不能为空")
    @ToMapAnno(name = "cardType")
    private String cardType;
    //卡号
    @NotEmpty(message = "就诊卡不能为空")
    @ToMapAnno(name = "yac005")
    private String yac005;
    //与账号关系
    @NotEmpty(message = "关系不能为空")
    private String relationship;

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

    public String getAac003() {
        return aac003;
    }

    public void setAac003(String aac003) {
        this.aac003 = aac003;
    }

    public String getYae098() {
        return yae098;
    }

    public void setYae098(String yae098) {
        this.yae098 = yae098;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAac004() {
        return aac004;
    }

    public void setAac004(String aac004) {
        this.aac004 = aac004;
    }

    public String getAac006() {
        return aac006;
    }

    public void setAac006(String aac006) {
        this.aac006 = aac006;
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

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
