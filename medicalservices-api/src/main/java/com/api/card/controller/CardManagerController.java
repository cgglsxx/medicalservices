package com.api.card.controller;



import com.alibaba.dubbo.config.annotation.Reference;
import com.api.card.service.CardForPatService;
import com.api.dto.card.BindCardDto;
import com.api.dto.card.BookingDto;
import com.api.dto.card.QueryCardDto;
import com.api.dto.card.UnBindCardDto;
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
@Api(value = "就诊卡服务", description = "就诊卡模块接口")
public class CardManagerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Reference
    CardForPatService cardForPatService;
    @ApiOperation(value = "无卡注册", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/card/saveBooking", method = RequestMethod.POST)
    public ResultBody saveBooking(@RequestBody @ApiParam(value = "dto",required = true)@ModelAttribute @Valid BookingDto dto,
                            BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        //todo 调用银联接口验证身份信息
        //验证成功调用无卡注册服务
        return cardForPatService.saveBooking(dto);
    }
    @ApiOperation(value = "绑卡", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/card/saveBindCard", method = RequestMethod.POST)
    public ResultBody saveBindCard(@RequestBody @ApiParam(value = "dto",required = true)@ModelAttribute @Valid BindCardDto dto,
                                  BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        //调用服务
        return cardForPatService.saveBindCard(dto);
    }
    @ApiOperation(value = "解绑", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/card/updateUnBindCard", method = RequestMethod.POST)
    public ResultBody updateUnBindCard(@RequestBody @ApiParam(value = "dto",required = true)@ModelAttribute @Valid UnBindCardDto dto,
                                  BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        //调用服务
        return cardForPatService.updateUnBindCard(dto);
    }
    @ApiOperation(value = "查询绑卡信息", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/card/queryCardInfo", method = RequestMethod.POST)
    public ResultBody queryCardInfo(@RequestBody @ApiParam(value = "dto",required = true)@ModelAttribute @Valid QueryCardDto dto,
                                         BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        //调用服务
        return cardForPatService.queryCardInfo(dto);
    }
}
