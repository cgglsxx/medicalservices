package com.api.dto.register;


import com.api.dto.parentDto.ParentDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 生成挂号订单
 */
@ApiModel(value="生成挂号订单参数对象",description="生成挂号订单参数对象")
public class RegOrderSaveDto extends ParentDto {
    //账号id
    @ApiModelProperty(name="out_platform_id",value="账号id",example="2088990",required =true)
    @NotEmpty(message = "账号id不能为空")
    private String out_platform_id;
    //渠道
    @ApiModelProperty(name="channel",value="渠道",example="10",required =true)
    @NotEmpty(message = "渠道不能为空")
    private String channel;
    //身份证号
    @ApiModelProperty(name="idcard_no",value="身份证号",example="513029199008170677",required =true)
    @NotEmpty(message = "身份证不能为空")
    private String idcard_no;
    //排班ID
    @ApiModelProperty(name="tscid",value="排班ID",example="121",required =true)
    @NotEmpty(message = "排班id不能为空")
    private String tscid;
    //号源序号
    @ApiModelProperty(name="seqnum",value="号源序号(挂号时可空（若医院当日挂号只到号源，不分号序，则可以为空）)",example="1")
    private String seqnum;
    //挂号日期
    @ApiModelProperty(name="tscdate",value="挂号日期",example="2018-08-28",required =true)
    @NotEmpty(message = "挂号日期不能为空")
    private String tscdate;
    //预约挂号标志
    @ApiModelProperty(name="preregFlag",value="预约挂号标志(1-预约 2-挂号)",example="1",required =true)
    @NotEmpty(message = "预约挂号标志不能为空")
    private String preregFlag;
    //挂号类型
    @ApiModelProperty(name="regType",value="挂号类型(1-专家，2-专病，3-普通，4-特需门诊)",example="1",required =true)
    @NotEmpty(message = "挂号类型不能为空")
    private String regType;
    //科室代码
    @ApiModelProperty(name="deptId",value="科室代码",example="124",required =true)
    @NotEmpty(message = "科室代码不能为空")
    private String deptId;
    //医生代码
    @ApiModelProperty(name="drId",value="医生代码(普通挂号时，医生代码可以为空)",example="1204")
    private String drId;
    //上下午标志
    @ApiModelProperty(name="daySection",value="上下午标志(0-全天，1-上午，2-下午，3-夜间)",example="0")
    private String daySection;
    //是否扣院内账户
    @ApiModelProperty(name="whetherDed",value="是否扣院内账户(0-不从院内账户走，1-走院内账户)",example="0",required = true)
    @NotEmpty(message = "是否扣院内账户不能为空")
    private String whetherDed;
    //是否自费结算
    @ApiModelProperty(name="whetherSet",value="是否自费结算(0-根据病人医保代码结算，1自费结算)",example="1",required = true)
    @NotEmpty(message = "是否自费结算不能为空")
    private String whetherSet;
    //院内卡号
    @ApiModelProperty(name="hospitalcardNo",value="院内卡号(当选择院内卡支付时，卡号不能为空)",example="14533224698")
    private String hospitalcardNo;
    //院内支付密码
    @ApiModelProperty(name="password",value="院内支付密码(当选择院内卡支付时，院内支付密码不能为空)",example="3212113")
    private String password;
    //备注信息
    @ApiModelProperty(name="remark",value="备注信息",example="挂号订单")
    private String remark;

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
