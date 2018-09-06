package com.api.inpatient;


import com.alibaba.dubbo.config.annotation.Reference;
import com.api.dto.inpatient.InpatientCostQueryDto;
import com.api.dto.inpatient.InpatientInfoQueryDto;
import com.api.dto.inpatient.InpatientPrePaymentQueryDto;
import com.api.dto.inpatient.InpatientRecordsQueryDto;
import com.api.inpatient.service.InPatientService;
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
@Api(value = "住院服务", description = "住院服务模块接口")
public class InPatientController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Reference
    InPatientService inPatientService;
    @ApiOperation(value = "住院基本信息查询", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/inpatient/queryInpatientInfo", method = RequestMethod.POST)
    public ResultBody queryInpatientInfo(@RequestBody @ApiParam(value = "dto",required = true)@ModelAttribute @Valid InpatientInfoQueryDto dto,
                            BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        //调用服务
        return inPatientService.queryInpatientInfo(dto);
    }
    @ApiOperation(value = "查询住院已结账的费用清单", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/inpatient/queryInpCost", method = RequestMethod.POST)
    public ResultBody queryInpCost(@RequestBody @ApiParam(value = "dto",required = true)@ModelAttribute @Valid InpatientCostQueryDto dto,
                                         BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        //调用服务
        return inPatientService.queryInpCost(dto);
    }
    @ApiOperation(value = "住院预交款记录查询", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/inpatient/queryPrePayment", method = RequestMethod.POST)
    public ResultBody queryPrePayment(@RequestBody @ApiParam(value = "dto",required = true)@ModelAttribute @Valid InpatientPrePaymentQueryDto dto,
                                         BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        //调用服务
        return inPatientService.queryPrePayment(dto);
    }
    @ApiOperation(value = "住院历史记录查询", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/inpatient/inpHistoryRecord", method = RequestMethod.POST)
    public ResultBody inpHistoryRecord(@RequestBody @ApiParam(value = "dto",required = true)@ModelAttribute @Valid InpatientRecordsQueryDto dto,
                                         BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        //调用服务
        return inPatientService.inpHistoryRecord(dto);
    }



}
