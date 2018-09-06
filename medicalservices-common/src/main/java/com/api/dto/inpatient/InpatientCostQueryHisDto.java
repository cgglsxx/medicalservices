package com.api.dto.inpatient;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;

/**
 * 住院费用清单查询参数对象
 */
public class InpatientCostQueryHisDto extends ParentDto {
    //账单号
    @ToMapAnno(name = "interid")
    private String interid;
    //开始日期
    @ToMapAnno(name = "startingDate")
    private String startingDate;
    //结束日期
    @ToMapAnno(name = "closingDate")
    private String closingDate;
    public InpatientCostQueryHisDto(InpatientCostQueryDto dto){
        this.interid = dto.getInterid();
        this.startingDate = dto.getStartingDate();
        this.closingDate = dto.getClosingDate();
        super.setServiceId("queryInpCost");
    }
    public String getInterid() {
        return interid;
    }

    public void setInterid(String interid) {
        this.interid = interid;
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
}
