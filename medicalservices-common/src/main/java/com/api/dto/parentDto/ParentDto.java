package com.api.dto.parentDto;


import com.api.selfannotation.ToMapAnno;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * 查询医生信息参数dto
 */
public class ParentDto implements Serializable{
    //定点机构编号
    @NotEmpty(message = "机构编号不能为空")
    @ToMapAnno(name = "akb20")
    private String akb020;

    public String getAkb020() {
        return akb020;
    }

    public void setAkb020(String akb020) {
        this.akb020 = akb020;
    }

}
