package com.api.dto.register;


import com.api.dto.parentDto.ParentDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 挂号订单结算
 */
@ApiModel(value="挂号订单结算参数对象",description="挂号订单结算参数对象")
public class RegAccountDto extends ParentDto {
    //账号id
    @ApiModelProperty(name="out_platform_id",value="账号id",example="2088990",required =true)
    @NotEmpty(message = "账号id不能为空")
    private String out_platform_id;
    //渠道
    @ApiModelProperty(name="channel",value="渠道",example="10",required =true)
    @NotEmpty(message = "渠道不能为空")
    private String channel;
    //订单编号
    @ApiModelProperty(name="orderId",value="订单编号",example="36a43657f70f4538b2e913796768a984",required =true)
    @NotEmpty(message = "订单编号不能为空")
    private String orderId;
    //自付金额
    @ApiModelProperty(name="personAmt",value="自付金额",example="3.00",required =true)
    @NotEmpty(message = "自付金额不能为空")
    private String personAmt;
    //医保支付金额
    @ApiModelProperty(name="payMoney",value="医保支付金额",example="4.00",required =true)
    @NotEmpty(message = "医保支付金额不能为空")
    private String payMoney;
    //支付方式
    @ApiModelProperty(name="payway",value="支付方式(0-无第三方支付（即个人支付金额为0），1-支付宝，2-微信支付，3-银联卡支付，4-院内支付)",example="2",required = true)
    private String payway;
    //支付类型
    @ApiModelProperty(name="payType",value="支付类型(1 线上支付 2 当面付)",example="1",required = true)
    private String payType;
    //支付渠道
    @ApiModelProperty(name="payChannel",value="支付渠道（参照appCode）",example="1",required = true)
    private String payChannel;
    //支付流水号
    @ApiModelProperty(name="payTradeno",value="支付流水号",example="1322900000",required = true)
    private String payTradeno;

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

    public String getPersonAmt() {
        return personAmt;
    }

    public void setPersonAmt(String personAmt) {
        this.personAmt = personAmt;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getPayTradeno() {
        return payTradeno;
    }

    public void setPayTradeno(String payTradeno) {
        this.payTradeno = payTradeno;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
