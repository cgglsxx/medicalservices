package com.api.symptoms.service;


import com.api.dto.symptoms.BodysQueryDto;
import com.api.dto.symptoms.SymptomsDetailQueryDto;
import com.api.dto.symptoms.SymptomsListQueryDto;
import com.api.result.GlobalErrorInfoException;
import com.api.result.ResultBody;


/**
 * 智能导诊service
 */
public interface SymptomsService {

    /**
     *查询身体部位
     * @param dto
     * @return
     */
    ResultBody queryBodys(BodysQueryDto dto) throws GlobalErrorInfoException;

    /**
     * 医疗咨询列表查询
     * @param dto
     * @return
     */
    ResultBody querySymptomsList(SymptomsListQueryDto dto) throws GlobalErrorInfoException;

    /**
     * 医疗咨询详情内容查询
     * @param dto
     * @return
     */
    ResultBody querySymptomsWithContent(SymptomsDetailQueryDto dto) throws GlobalErrorInfoException;

}
