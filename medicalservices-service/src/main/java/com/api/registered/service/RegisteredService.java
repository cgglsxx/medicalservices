package com.api.registered.service;


import com.api.result.GlobalErrorInfoException;
import com.api.result.ResultBody;

import java.util.Map;

/**
 * 挂号模块service
 */
public interface RegisteredService {

    /**
     * 获取科室列表
     * @param param
     * @return
     */
    ResultBody getKsDeptInfo(Map param) throws GlobalErrorInfoException;

    /**
     * 获取医生列表
     * @param param
     * @return
     */
    ResultBody getKsRegDoctorInfo(Map param) throws GlobalErrorInfoException;

    /**
     * 号源信息查询
     * @param param
     * @return
     */
    ResultBody getRegScheduleInfo(Map param) throws GlobalErrorInfoException;
}
