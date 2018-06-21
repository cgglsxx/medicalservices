package com.api.registered.controller;


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
    @Autowired
    RegisteredService registeredService;
    @Autowired
    private MsgSender msgSender;
    @ApiOperation(value = "获取科室列表", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/registered/getKsDeptInfo", method = RequestMethod.GET)
    public ResultBody getKsDeptInfo(@ApiParam(value = "dto",required = true)@ModelAttribute @Valid DepartmentQueryDto dto,
                            BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        //调用服务
        return registeredService.getKsDeptInfo(ReflectMapUtil.beanToMap(dto));
    }
    @ApiOperation(value = "获取医生列表", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/registered/getKsRegDoctorInfo", method = RequestMethod.GET)
    public ResultBody getKsRegDoctorInfo(@ApiParam(value = "dto",required = true)@ModelAttribute @Valid DoctorsQueryDto dto,
                                  BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        //调用服务
        return registeredService.getKsRegDoctorInfo(ReflectMapUtil.beanToMap(dto));
    }
    @ApiOperation(value = "获取号源信息", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/registered/getRegScheduleInfo", method = RequestMethod.GET)
    public ResultBody getRegScheduleInfo(@ApiParam(value = "dto",required = true)@ModelAttribute @Valid RegScheduleInfoQueryDto dto,
                                  BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        //调用服务
        return registeredService.getRegScheduleInfo(ReflectMapUtil.beanToMap(dto));
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
