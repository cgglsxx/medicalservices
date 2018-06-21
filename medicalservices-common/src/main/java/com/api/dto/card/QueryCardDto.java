package com.api.dto.card;


import com.api.dto.parentDto.ParentDto;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 查询绑卡参数dto
 */
public class QueryCardDto extends ParentDto {
    //账号id
    @NotEmpty(message = "账号id不能为空")
    private String yad901;
    //渠道
    @NotEmpty(message = "渠道不能为空")
    private String yad961;


    public String getYad901() {
        return yad901;
    }

    public void setYad901(String yad901) {
        this.yad901 = yad901;
    }

    public String getYad961() {
        return yad961;
    }

    public void setYad961(String yad961) {
        this.yad961 = yad961;
    }


}
