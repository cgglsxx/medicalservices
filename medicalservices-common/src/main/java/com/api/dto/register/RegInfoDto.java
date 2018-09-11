package com.api.dto.register;


import com.api.dto.parentDto.ParentDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 门诊已预约、挂号信息查询参数对象
 */
@ApiModel(value="门诊已预约、挂号信息查询参数对象",description="门诊已预约、挂号信息查询参数对象")
public class RegInfoDto extends ParentDto {
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
    //开始日期
    @ApiModelProperty(name="startingDate",value="开始日期(yyyy-MM-dd)",example="2018-09-03",required = true)
    @NotEmpty(message = "开始日期不能为空")
    private String startingDate;
    //结束日期
    @ApiModelProperty(name="closingDate",value="结束日期(yyyy-MM-dd)",example="2018-09-03",required = true)
    @NotEmpty(message = "结束日期不能为空")
    private String closingDate;
    //预约或挂号
    @ApiModelProperty(name="isReg",value="预约或挂号(0 预约 1挂号)",example="1",required = true)
    @NotEmpty(message = "预约或挂号标识不能为空")
    private String isReg;

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

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public String getIsReg() {
        return isReg;
    }

    public void setIsReg(String isReg) {
        this.isReg = isReg;
    }

    public String getIdcard_no() {
        return idcard_no;
    }

    public void setIdcard_no(String idcard_no) {
        this.idcard_no = idcard_no;
    }
}
