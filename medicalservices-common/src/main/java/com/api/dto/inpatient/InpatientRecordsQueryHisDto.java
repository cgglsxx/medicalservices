package com.api.dto.inpatient;


import com.api.dto.parentDto.ParentDto;
import com.api.selfannotation.ToMapAnno;

/**
 * 住院历史记录查询参数对象
 */
public class InpatientRecordsQueryHisDto extends ParentDto {
    //病人唯一识别码
    @ToMapAnno(name = "patId")
    private String patId;
    //开始日期
    @ToMapAnno(name = "startingDate")
    private String startingDate;
    //结束日期
    @ToMapAnno(name = "closingDate")
    private String closingDate;
    public InpatientRecordsQueryHisDto(InpatientRecordsQueryDto dto){
        this.startingDate = dto.getStartingDate();
        this.closingDate = dto.getClosingDate();
        super.setServiceId("inpHistoryRecord");
    }

    public String getPatId() {
        return patId;
    }

    public void setPatId(String patId) {
        this.patId = patId;
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
