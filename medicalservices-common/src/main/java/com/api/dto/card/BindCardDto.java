package com.api.dto.card;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 绑卡参数dto
 */
@ApiModel(value="绑卡参数对象",description="绑卡参数对象")
public class BindCardDto extends ParentDto {
    //账号id
    @ApiModelProperty(name="out_platform_id",value="第三方个人id",example="2088999",required =true)
    @NotEmpty(message = "第三方账号id不能为空")
    private String out_platform_id;
    //渠道
    @ApiModelProperty(name="channel",value="渠道 (10:表示公众号 20:表示生活号)",example="10",required =true)
    @NotEmpty(message = "渠道不能为空")
    private String channel;
    //身份证号
    @ApiModelProperty(name="idcard_no",value="身份证号(就诊人身份证号,就诊人为成人时该字段必传)",example="513029199008170677")
    @ToMapAnno(name = "certId")
    private String idcard_no;
    //监护人身份证号
    @ApiModelProperty(name="guardianId",value="监护人身份证号(1、如果是成人，监护人身份证号可为空；2、如果是未成年，身份证号为监护人身份证号)",example="513029199008170677")
    @ToMapAnno(name = "guardianId")
    private String guardianId;
    //姓名
    @ApiModelProperty(name="pat_name",value="姓名",example="欧总",required = true)
    @NotEmpty(message = "姓名不能为空")
    @ToMapAnno(name = "patientName")
    private String pat_name;
    //电话
    @ApiModelProperty(name="mobile",value="电话",example="18782934623",required = true)
    @NotEmpty(message = "电话不能为空")
    @ToMapAnno(name = "phone")
    private String mobile;
    //地址
    @ApiModelProperty(name="address",value="地址",example="成都市")
    private String address;
    //卡类型
    @ApiModelProperty(name="cardType",value="卡类型 (1-就诊卡，2-医保卡，3-居民健康卡，4-身份证，5-病人唯一识别码（病历号、门诊号等）)",example="4",required = true)
    @NotEmpty(message = "卡类型不能为空")
    private String cardType;
    //卡号
    @ApiModelProperty(name="cardNo",value="卡号",example="513029199008170677",required = true)
    @NotEmpty(message = "卡号不能为空")
    private String cardNo;
    //与账号关系
    @ApiModelProperty(name="relationship",value="与账号关系 (1本人 2父母 3其他)",example="1",required = true)
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
