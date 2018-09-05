package com.api.clinic.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.api.clinic.service.ClinicService;
import com.api.dto.clinic.ClinicAccountDto;
import com.api.dto.clinic.ClinicBillDetailQueryDto;
import com.api.dto.clinic.ClinicBillInfoQueryDto;
import com.api.dto.clinic.ClinicPreAccountDto;
import com.api.result.GlobalErrorInfoException;
import com.api.result.ResultBody;
import com.api.result.messageenum.RegisteredErrorInfoEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Api(value = "门诊缴费服务", description = "门诊缴费服务模块接口")
public class ClinicController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Reference
    ClinicService clinicService;
    @ApiOperation(value = "门诊缴费信息查询", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/clinic/queryClinicBillInfo", method = RequestMethod.POST)
    public ResultBody queryClinicBillInfo(@RequestBody @ApiParam(value = "dto",required = true)@ModelAttribute @Valid ClinicBillInfoQueryDto dto,
                            BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        //调用服务
        return clinicService.queryClinicBillInfo(dto);
    }
    @ApiOperation(value = "门诊缴费单明细查询", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/clinic/queryClinicBillDetail", method = RequestMethod.POST)
    public ResultBody queryClinicBillDetail(@RequestBody @ApiParam(value = "dto",required = true)@ModelAttribute @Valid ClinicBillDetailQueryDto dto,
                            BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        //调用服务
        return clinicService.queryClinicBillDetail(dto);
    }
    @ApiOperation(value = "门诊缴费预算", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/registered/clinicPreAccount", method = RequestMethod.POST)
    public ResultBody clinicPreAccount(@RequestBody @ApiParam(value = "dto",required = true)@ModelAttribute @Valid ClinicPreAccountDto dto,
                                       BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        return clinicService.clinicPreAccount(dto);
    }
    @ApiOperation(value = "门诊缴费结算", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/registered/clinicAccount", method = RequestMethod.POST)
    public ResultBody clinicAccount(@RequestBody @ApiParam(value = "dto",required = true)@ModelAttribute @Valid ClinicAccountDto dto,
                                       BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        return clinicService.clinicAccount(dto);
    }


}
