package com.api.registered.service;


import com.api.dto.register.RegAccountDto;
import com.api.dto.register.RegInfoDto;
import com.api.dto.register.RegOrderSaveDto;
import com.api.dto.register.RegRefundDto;
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
    /**
     * 查询当日门诊可挂号号源信息
     * @param param
     * @return
     */
    ResultBody queryRegSource(Map param) throws GlobalErrorInfoException;
    /**
     * 生成挂号订单(锁号和预算)
     * @param dto
     * @return
     */
    ResultBody saveLockReg(RegOrderSaveDto dto) throws GlobalErrorInfoException;
    /**
     * 挂号订单结算
     * @param dto
     * @return
     */
    ResultBody regAccount(RegAccountDto dto) throws GlobalErrorInfoException;
    /**
     * 门诊已预约、挂号信息查询
     * @param dto
     * @return
     */
    ResultBody queryRegisterInformation(RegInfoDto dto) throws GlobalErrorInfoException;
    /**
     * 门诊已预约、挂号信息查询
     * @param dto
     * @return
     */
    ResultBody cancelRegAccount(RegRefundDto dto) throws GlobalErrorInfoException;
}
