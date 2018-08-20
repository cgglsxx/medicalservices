package com.api.registered.service.impl;

import com.api.adapter.impl.HISCommonInterfaceTransAdapterImpl;
import com.api.registered.service.RegisteredService;
import com.api.result.GlobalErrorInfoException;
import com.api.result.ResultBody;
import com.api.setting.HisSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 挂号服务实现
 */
@Transactional
@Configuration
@EnableConfigurationProperties(HisSetting.class)
@Service("registeredService")
public class RegisteredServiceImpl implements RegisteredService {
    @Autowired
    HisSetting setting;
    @Autowired
    HISCommonInterfaceTransAdapterImpl service;

    @Override
    public ResultBody querySectionInformation(Map param) throws GlobalErrorInfoException {
        return serviceInvoke(param);
    }

    @Override
    public ResultBody queryDrInformation(Map param) throws GlobalErrorInfoException {
        return serviceInvoke(param);
    }

    @Override
    public ResultBody querySectionSourceInformation(Map param) throws GlobalErrorInfoException {
        return serviceInvoke(param);
    }
    @Override
    public ResultBody queryDrSourceInformation(Map param) throws GlobalErrorInfoException {
        return serviceInvoke(param);
    }
    @Override
    public ResultBody querySectionDrInformation(Map param) throws GlobalErrorInfoException {
        return serviceInvoke(param);
    }
    @Override
    public ResultBody queryObtainDrSection(Map param) throws GlobalErrorInfoException {
        return serviceInvoke(param);
    }
    private ResultBody serviceInvoke(Map param) throws GlobalErrorInfoException {
        return service.sendMsg(param, setting.getUrl());
    }
}
