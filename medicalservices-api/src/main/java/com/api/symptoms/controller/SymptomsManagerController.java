package com.api.symptoms.controller;



import com.alibaba.dubbo.config.annotation.Reference;
import com.api.dto.symptoms.BodysQueryDto;
import com.api.dto.symptoms.SymptomsDetailQueryDto;
import com.api.dto.symptoms.SymptomsListQueryDto;
import com.api.result.GlobalErrorInfoException;
import com.api.result.ResultBody;
import com.api.result.messageenum.RegisteredErrorInfoEnum;
import com.api.symptoms.service.SymptomsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Api(value = "智能导诊服务", description = "智能导诊服务模块接口")
public class SymptomsManagerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Reference
    SymptomsService symptomsService;
    @ApiOperation(value = "部位查询", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/symptoms/queryBodys", method = RequestMethod.POST)
    public ResultBody queryBodys(@RequestBody @ApiParam(value = "dto",required = true)@ModelAttribute @Valid BodysQueryDto dto,
                            BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        return symptomsService.queryBodys(dto);
    }
    @ApiOperation(value = "症状列表", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/symptoms/querySymptomsList", method = RequestMethod.POST)
    public ResultBody querySymptomsList(@RequestBody @ApiParam(value = "dto",required = true)@ModelAttribute @Valid SymptomsListQueryDto dto,
                                 BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        return symptomsService.querySymptomsList(dto);
    }
    @ApiOperation(value = "症状导诊详情", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/symptoms/querySymptomsWithContent", method = RequestMethod.POST)
    public ResultBody querySymptomsWithContent(@RequestBody @ApiParam(value = "dto",required = true)@ModelAttribute @Valid SymptomsDetailQueryDto dto,
                                 BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        return symptomsService.querySymptomsWithContent(dto);
    }
}
