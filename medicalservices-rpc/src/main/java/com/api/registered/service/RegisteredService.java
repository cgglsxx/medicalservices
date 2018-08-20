package com.api.registered.service;


import com.api.result.GlobalErrorInfoException;
import com.api.result.ResultBody;

import java.util.Map;

/**
 * 挂号模块service
 */
public interface RegisteredService {

    /**
     * 获取当班科室列表
     * @param param
     * @return
     */
    ResultBody querySectionInformation(Map param) throws GlobalErrorInfoException;

    /**
     * 获取当班医生列表
     * @param param
     * @return
     */
    ResultBody queryDrInformation(Map param) throws GlobalErrorInfoException;

    /**
     * 获取科室当班号源信息查询
     * @param param
     * @return
     */
    ResultBody querySectionSourceInformation(Map param) throws GlobalErrorInfoException;
    /**
     * 获取当班医生号源信息查询
     * @param param
     * @return
     */
    ResultBody queryDrSourceInformation(Map param) throws GlobalErrorInfoException;
    /**
     * 根据医院代码获取有排班的科室信息和有排班的医生所在科室
     * @param param
     * @return
     */
    ResultBody querySectionDrInformation(Map param) throws GlobalErrorInfoException;
    /**
     * 根据排班类别获取当班科室当班医生
     * @param param
     * @return
     */
    ResultBody queryObtainDrSection(Map param) throws GlobalErrorInfoException;
}
