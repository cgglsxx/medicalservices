package com.api.dto.clinic;


import com.api.dto.parentDto.ParentDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 门诊缴费预算参数对象
 */
@ApiModel(value="门诊缴费预算参数对象",description="门诊缴费预算参数对象")
public class ClinicPreAccountDto extends ParentDto {
    //账号id
    @ApiModelProperty(name="out_platform_id",value="账号id",example="2088990",required =true)
    @NotEmpty(message = "账号id不能为空")
    private String out_platform_id;
    //渠道
    @ApiModelProperty(name="channel",value="渠道",example="10",required =true)
    @NotEmpty(message = "渠道不能为空")
    private String channel;
    //身份证
    @ApiModelProperty(name="idcard_no",value="身份证",example="512039199009170988",required =true)
    @NotEmpty(message = "身份证不能为空")
    private String idcard_no;
    //挂号单号
    @ApiModelProperty(name="regId",value="挂号单号",example="123422",required =true)
    @NotEmpty(message = "挂号单号不能为空")
    private String regId;
    //单据列表（或处方号）
    @ApiModelProperty(name="receiptList",value="单据列表（多个编号用“|”分隔 (根据HIS情况可以传空)）",example="123422|23334",required =true)
    @NotEmpty(message = "挂号单号不能为空")
    private String receiptList;
    //费用类型列表
    @ApiModelProperty(name="chargeTypeList",value="费用类型列表（多个类别用“|”分隔，与单据列表对应 (根据HIS情况可以传空)）",example="123422|23334")
    @NotEmpty(message = "费用类型列表不能为空")
    private String chargeTypeList;
    //自付金额列表
    @ApiModelProperty(name="personAmtList",value="自付金额列表（多个类别用“|”分隔，与单据列表对应，患者应该自己承担的费用（=总金额-优惠金额-医保支付））",example="123422|23334",required =true)
    @NotEmpty(message = "自付金额列表不能为空")
    private String personAmtList;
    //收费窗口代码
    @ApiModelProperty(name="tollwindowNo",value="收费窗口代码",example="123")
    @NotEmpty(message = "收费窗口代码不能为空")
    private String tollwindowNo;
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

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getReceiptList() {
        return receiptList;
    }

    public void setReceiptList(String receiptList) {
        this.receiptList = receiptList;
    }

    public String getChargeTypeList() {
        return chargeTypeList;
    }

    public void setChargeTypeList(String chargeTypeList) {
        this.chargeTypeList = chargeTypeList;
    }

    public String getPersonAmtList() {
        return personAmtList;
    }

    public void setPersonAmtList(String personAmtList) {
        this.personAmtList = personAmtList;
    }

    public String getTollwindowNo() {
        return tollwindowNo;
    }

    public void setTollwindowNo(String tollwindowNo) {
        this.tollwindowNo = tollwindowNo;
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
}
