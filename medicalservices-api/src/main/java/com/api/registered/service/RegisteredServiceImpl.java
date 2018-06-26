package com.api.registered.service;


import com.alibaba.dubbo.config.annotation.Reference;
import com.api.result.GlobalErrorInfoException;
import com.api.result.ResultBody;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 挂号服务实现
 */
@Component
public class RegisteredServiceImpl implements RegisteredService {
    @Reference
    RegisteredService registeredService;
    @Override
    public ResultBody getKsDeptInfo(Map param) throws GlobalErrorInfoException {
        return registeredService.getKsDeptInfo(param);
    }

    @Override
    public ResultBody getKsRegDoctorInfo(Map param) throws GlobalErrorInfoException {
        return registeredService.getKsRegDoctorInfo(param);
    }

    @Override
    public ResultBody getRegScheduleInfo(Map param) throws GlobalErrorInfoException {
        return registeredService.getRegScheduleInfo(param);
    }
}
