package com.api.dto.register;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;

/**
 * 门诊预约、挂号预算dto
 */
public class RegPreAccountDto extends ParentDto {
    //病人唯一码
    @ToMapAnno(name = "patId")
    private String patId;
    //卡号
    @ToMapAnno(name = "cardNo")
    private String cardNo;
    //卡类型
    @ToMapAnno(name = "cardType")
    private String cardType;
    //预约挂号标志
    @ToMapAnno(name = "preregFlag")
    private String preregFlag;
    //挂号类别
    @ToMapAnno(name = "regType")
    private String regType;
    //科室代码
    @ToMapAnno(name = "deptId")
    private String deptId;
    //医生代码
    @ToMapAnno(name = "drId")
    private String drId;
    //排班ID
    @ToMapAnno(name = "tscid")
    private String tscid;
    //号源序号
    @ToMapAnno(name = "seqnum")
    private String seqnum;
    //挂号日期
    @ToMapAnno(name = "tscdate")
    private String tscdate;
    //上下午标志
    @ToMapAnno(name = "daySection")
    private String daySection;
    //是否扣院内账户
    @ToMapAnno(name = "whetherDed")
    private String whetherDed;
    //是否自费结算
    @ToMapAnno(name = "whetherSet")
    private String whetherSet;
    //院内卡号
    @ToMapAnno(name = "hospitalcardNo")
    private String hospitalcardNo;
    //院内支付密码
    @ToMapAnno(name = "password")
    private String password;
    //备注信息
    @ToMapAnno(name = "remark")
    private String remark;

    public RegPreAccountDto(RegOrderSaveDto dto){
        this.tscid = dto.getTscid();
        this.seqnum = dto.getSeqnum();
        this.tscdate = dto.getTscdate();
        this.preregFlag = dto.getPreregFlag();
        this.regType = dto.getRegType();
        this.deptId = dto.getDeptId();
        this.drId = dto.getDrId();
        this.daySection = dto.getDaySection();
        this.whetherDed = dto.getWhetherDed();
        this.whetherSet = dto.getWhetherSet();
        this.hospitalcardNo = dto.getHospitalcardNo();
        this.password = dto.getPassword();
        this.remark = dto.getRemark();
        super.setServiceId("regPreAccount");
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

    public String getTscid() {
        return tscid;
    }

    public void setTscid(String tscid) {
        this.tscid = tscid;
    }

    public String getSeqnum() {
        return seqnum;
    }

    public void setSeqnum(String seqnum) {
        this.seqnum = seqnum;
    }

    public String getTscdate() {
        return tscdate;
    }

    public void setTscdate(String tscdate) {
        this.tscdate = tscdate;
    }

    public String getPreregFlag() {
        return preregFlag;
    }

    public void setPreregFlag(String preregFlag) {
        this.preregFlag = preregFlag;
    }

    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDrId() {
        return drId;
    }

    public void setDrId(String drId) {
        this.drId = drId;
    }

    public String getDaySection() {
        return daySection;
    }

    public void setDaySection(String daySection) {
        this.daySection = daySection;
    }

    public String getWhetherDed() {
        return whetherDed;
    }

    public void setWhetherDed(String whetherDed) {
        this.whetherDed = whetherDed;
    }

    public String getWhetherSet() {
        return whetherSet;
    }

    public void setWhetherSet(String whetherSet) {
        this.whetherSet = whetherSet;
    }

    public String getHospitalcardNo() {
        return hospitalcardNo;
    }

    public void setHospitalcardNo(String hospitalcardNo) {
        this.hospitalcardNo = hospitalcardNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
