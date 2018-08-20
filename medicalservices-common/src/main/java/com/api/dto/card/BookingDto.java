package com.api.dto.card;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 无卡注册参数dto
 */
public class BookingDto extends ParentDto {
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
    //姓名
    @NotEmpty(message = "性别不能为空")
    @ToMapAnno(name = "sex")
    private String sex;
    //生日
    @NotEmpty(message = "生日不能为空")
    @ToMapAnno(name = "birthday")
    private String birthday;
    //电话
    @NotEmpty(message = "电话不能为空")
    @ToMapAnno(name = "phone")
    private String mobile;
    //地址
    @ToMapAnno(name = "address")
    private String address;
    //卡类型
    @NotEmpty(message = "卡类型不能为空")
    @ToMapAnno(name = "cardType")
    private String cardType;
    //卡号
    @NotEmpty(message = "卡号不能为空")
    @ToMapAnno(name = "cardNo")
    private String cardNo;
    //医保代码
    @ToMapAnno(name = "ybdm")
    private String ybdm;
    //创建日期
    @NotEmpty(message = "创建日期不能为空")
    @ToMapAnno(name = "CreateDate")
    private String CreateDate;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public String getYbdm() {
        return ybdm;
    }

    public void setYbdm(String ybdm) {
        this.ybdm = ybdm;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
