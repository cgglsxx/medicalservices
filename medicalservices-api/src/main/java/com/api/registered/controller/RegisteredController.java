package com.api.registered.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.api.dto.register.*;
import com.api.mq.config.RabbitConfig;
import com.api.mq.model.Msg;
import com.api.mq.scenes.sendmq.MsgSender;
import com.api.registered.service.RegisteredService;
import com.api.result.GlobalErrorInfoException;
import com.api.result.ResultBody;
import com.api.result.messageenum.RegisteredErrorInfoEnum;
import com.api.util.ReflectMapUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@Api(value = "挂号服务", description = "挂号模块接口")
public class RegisteredController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Reference
    RegisteredService registeredService;
    @Autowired
    private MsgSender msgSender;
    @ApiOperation(value = "获取当班科室列表", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/registered/querySectionInformation", method = RequestMethod.GET)
    public ResultBody querySectionInformation(@ApiParam(value = "dto",required = true)@ModelAttribute @Valid DepartmentQueryDto dto,
                            BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        //调用服务
        dto.setServiceId("querySectionInformation");
        return registeredService.querySectionInformation(ReflectMapUtil.beanToMap(dto));
    }
    @ApiOperation(value = "获取当班医生列表", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/registered/queryDrInformation", method = RequestMethod.GET)
    public ResultBody queryDrInformation(@ApiParam(value = "dto",required = true)@ModelAttribute @Valid DoctorsQueryDto dto,
                                  BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        //调用服务
        dto.setServiceId("queryDrInformation");
        return registeredService.queryDrInformation(ReflectMapUtil.beanToMap(dto));
    }
    @ApiOperation(value = "获取当班科室号源信息", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/registered/querySectionSourceInformation", method = RequestMethod.GET)
    public ResultBody querySectionSourceInformation(@ApiParam(value = "dto",required = true)@ModelAttribute @Valid RegScheduleInfoQueryDto dto,
                                  BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        //调用服务
        dto.setServiceId("querySectionSourceInformation");
        return registeredService.querySectionSourceInformation(ReflectMapUtil.beanToMap(dto));
    }
    @ApiOperation(value = "获取当班医生号源信息", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/registered/queryDrSourceInformation", method = RequestMethod.GET)
    public ResultBody queryDrSourceInformation(@ApiParam(value = "dto",required = true)@ModelAttribute @Valid RegScheduleInfoOfDrQueryDto dto,
                                               BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        //调用服务
        dto.setServiceId("queryDrSourceInformation");
        return registeredService.queryDrSourceInformation(ReflectMapUtil.beanToMap(dto));
    }
    @ApiOperation(value = "当班科室和医生所在科室信息查询", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/registered/querySectionDrInformation", method = RequestMethod.GET)
    public ResultBody querySectionDrInformation(@ApiParam(value = "dto",required = true)@ModelAttribute @Valid DepartmentForDrAndDeptQueryDto dto,
                                               BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        //调用服务
        dto.setServiceId("querySectionDrInformation");
        return registeredService.querySectionDrInformation(ReflectMapUtil.beanToMap(dto));
    }
    @ApiOperation(value = "根据排班类别获取当班科室当班医生", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/registered/queryObtainDrSection", method = RequestMethod.GET)
    public ResultBody queryObtainDrSection(@ApiParam(value = "dto",required = true)@ModelAttribute @Valid ObtainDrSectionQueryDto dto,
                                               BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        //调用服务
        dto.setServiceId("ObtainDrSection");
        return registeredService.queryObtainDrSection(ReflectMapUtil.beanToMap(dto));
    }
    @ApiOperation(value = "生成挂号订单", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/registered/saveLockReg", method = RequestMethod.GET)
    public ResultBody saveLockReg(@ApiParam(value = "dto",required = true)@ModelAttribute @Valid RegOrderSaveDto dto,
                                         BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        //todo step1 调用his接口锁号
        //todo step2 生成系统挂号订单
        //todo step3 启用30秒后不支付自动取消锁号
        Msg msg = new Msg();
        msg.setCount(1);
        msg.setTime("10000");//测试10秒钟后调用取消锁号
        msgSender.sendToMqForDelayCancelReg(msg);
        //调用服务
        return new ResultBody();
    }
    @ApiOperation(value = "挂号缴费保存", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/registered/saveReg", method = RequestMethod.GET)
    public ResultBody saveReg(@ApiParam(value = "dto",required = true)@ModelAttribute @Valid RegPaySaveDto dto,
                                         BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        //todo step1 保存支付数据
        //todo step2 异步通知his
        Msg msg = new Msg();
        msg.setCount(1);
        msg.setQueue(RabbitConfig.PROCESS_REGPAY_QUEUE);
        msgSender.sendToMqForNoticeHis(msg);
        return new ResultBody();
    }
}
