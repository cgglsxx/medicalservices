package com.api.dto.inpatient;


import com.api.dto.parentDto.ParentDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 住院预交金缴纳参数对象
 */
@ApiModel(value="住院预交金缴纳参数对象",description="住院预交金缴纳参数对象")
public class InpatientPrePaymentDto extends ParentDto {
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
    //支付金额
    @ApiModelProperty(name="preAmt",value="支付金额",example="33.00",required =true)
    @NotEmpty(message = "支付金额不能为空")
    private String preAmt;
    //支付者姓名
    @ApiModelProperty(name="payerName",value="支付者姓名",example="肖**",required = true)
    @NotEmpty(message = "支付者姓名不能为空")
    private String payerName;
    //订单编号
    @ApiModelProperty(name="orderId",value="订单编号",example="36a43657f70f4538b2e913796768a984",required =true)
    @NotEmpty(message = "订单编号不能为空")
    private String orderId;
    //支付方式
    @ApiModelProperty(name="payway",value="支付方式(0-无第三方支付（即个人支付金额为0），1-支付宝，2-微信支付，3-银联卡支付，4-院内支付)",example="2",required = true)
    @NotEmpty(message = "支付方式不能为空")
    private String payway;
    //支付流水号
    @ApiModelProperty(name="payTradeno",value="支付流水号",example="1322900000",required = true)
    @NotEmpty(message = "支付流水号不能为空")
    private String payTradeno;
    //支付类型
    @ApiModelProperty(name="payType",value="支付类型(1 线上支付 2 当面付)",example="1",required = true)
    @NotEmpty(message = "支付类型不能为空")
    private String payType;

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

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

    public String getPreAmt() {
        return preAmt;
    }

    public void setPreAmt(String preAmt) {
        this.preAmt = preAmt;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }


    public String getPayTradeno() {
        return payTradeno;
    }

    public void setPayTradeno(String payTradeno) {
        this.payTradeno = payTradeno;
    }
}
