package com.api.dto.inpatient;


import com.api.dto.parentDto.ParentDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 住院预交款记录查询参数对象
 */
@ApiModel(value="住院预交款记录查询参数对象",description="住院预交款记录查询参数对象")
public class InpatientPrePaymentQueryDto extends ParentDto {
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
    //账单号
    @ApiModelProperty(name="interid",value="账单号",example="34562211",required =true)
    @NotEmpty(message = "账单号不能为空")
    private String interid;
    //支付方式
    @ApiModelProperty(name="payWay",value="支付方式(0-全部1-支付宝，2-微信支付，3-银联卡支付4-现金 5-MISPos)",example="0",required = true)
    @NotEmpty(message = "支付方式不能为空")
    private String payWay;

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

    public String getInterid() {
        return interid;
    }

    public void setInterid(String interid) {
        this.interid = interid;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }
}
