package com.api.dto.card;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 绑卡参数dto
 */
public class BindCardDto extends ParentDto {
    //账号id
    @NotEmpty(message = "第三方账号id不能为空")
    private String out_platform_id;
    //渠道
    @NotEmpty(message = "渠道不能为空")
    private String channel;
    //身份证号
    @ToMapAnno(name = "certId")
    private String idcard_no;
    //监护人身份证号
    @ToMapAnno(name = "guardianId")
    private String guardianId;
    //姓名
    @NotEmpty(message = "姓名不能为空")
    @ToMapAnno(name = "patientName")
    private String pat_name;
    //电话
    @NotEmpty(message = "电话不能为空")
    @ToMapAnno(name = "phone")
    private String mobile;
    //地址
    private String address;
    //卡类型
    @NotEmpty(message = "卡类型不能为空")
    private String cardType;
    //卡号
    @NotEmpty(message = "卡号不能为空")
    private String cardNo;
    //与账号关系
    @NotEmpty(message = "关系不能为空")
    private String relationship;

    public String getOut_platform_id() {
        return out_platform_id;
    }

    public void setOut_platform_id(String out_platform_id) {
        this.out_platform_id = out_platform_id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getIdcard_no() {
        return idcard_no;
    }

    public void setIdcard_no(String idcard_no) {
        this.idcard_no = idcard_no;
    }

    public String getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(String guardianId) {
        this.guardianId = guardianId;
    }

    public String getPat_name() {
        return pat_name;
    }

    public void setPat_name(String pat_name) {
        this.pat_name = pat_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }


    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
