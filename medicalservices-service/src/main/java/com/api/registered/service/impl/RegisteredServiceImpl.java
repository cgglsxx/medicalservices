package com.api.registered.service.impl;

import com.api.adapter.HISTransAdapterContainer;
import com.api.constant.IConst;
import com.api.registered.service.RegisteredService;
import com.api.result.GlobalErrorInfoException;
import com.api.result.ResultBody;
import com.api.setting.HisSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 挂号服务实现
 */
@Service
public class RegisteredServiceImpl implements RegisteredService {
    @Autowired
    HisSetting setting;

    @Override
    public ResultBody getKsDeptInfo(Map param) throws GlobalErrorInfoException {
        return HISTransAdapterContainer.getAdapter(setting.getKey()).sendMsg(param, IConst.GETKSDEPTINFO);
    }

    @Override
    public ResultBody getKsRegDoctorInfo(Map param) throws GlobalErrorInfoException {
        return HISTransAdapterContainer.getAdapter(setting.getKey()).sendMsg(param, IConst.GETKSREGDOCTORINFO);
    }

    @Override
    public ResultBody getRegScheduleInfo(Map param) throws GlobalErrorInfoException {
        return HISTransAdapterContainer.getAdapter(setting.getKey()).sendMsg(param, IConst.GETREGSCHEDULEINFO);
    }
}
