package com.api.article.controller;



import com.alibaba.dubbo.config.annotation.Reference;
import com.api.article.service.ArticleService;
import com.api.card.service.CardForPatService;
import com.api.dto.article.ArticleDetailQueryDto;
import com.api.dto.article.ArticleListQueryDto;
import com.api.dto.article.CatesQueryDto;
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
@Api(value = "医疗咨讯服务", description = "医疗咨讯服务模块接口")
public class ArticleManagerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Reference
    ArticleService articleService;
    @ApiOperation(value = "医疗咨讯分类", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/article/queryCates", method = RequestMethod.POST)
    public ResultBody queryCates(@RequestBody @ApiParam(value = "dto",required = true)@ModelAttribute @Valid CatesQueryDto dto,
                            BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        return articleService.queryCates(dto);
    }
    @ApiOperation(value = "医疗咨讯列表", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/article/queryArticleList", method = RequestMethod.POST)
    public ResultBody queryArticleList(@RequestBody @ApiParam(value = "dto",required = true)@ModelAttribute @Valid ArticleListQueryDto dto,
                                 BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        return articleService.queryArticleList(dto);
    }
    @ApiOperation(value = "医疗咨讯详情", notes = "", response = ResultBody.class)
    @RequestMapping(value = "/api/article/queryArticleWithContent", method = RequestMethod.POST)
    public ResultBody queryArticleWithContent(@RequestBody @ApiParam(value = "dto",required = true)@ModelAttribute @Valid ArticleDetailQueryDto dto,
                                 BindingResult bindingResult) throws GlobalErrorInfoException {
        //验证参数
        if(bindingResult.hasErrors()){
            throw new GlobalErrorInfoException(RegisteredErrorInfoEnum.PARAMS_NO_COMPLETE);
        }
        return articleService.queryArticleWithContent(dto);
    }
}
