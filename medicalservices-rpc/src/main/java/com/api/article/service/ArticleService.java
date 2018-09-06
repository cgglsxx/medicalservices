package com.api.article.service;


import com.api.dto.article.ArticleDetailQueryDto;
import com.api.dto.article.ArticleListQueryDto;
import com.api.dto.article.CatesQueryDto;
import com.api.result.GlobalErrorInfoException;
import com.api.result.ResultBody;


/**
 * 就诊卡建档模块模块service
 */
public interface ArticleService {

    /**
     *查询医疗咨询分类
     * @param dto
     * @return
     */
    ResultBody queryCates(CatesQueryDto dto) throws GlobalErrorInfoException;

    /**
     * 医疗咨询列表查询
     * @param dto
     * @return
     */
    ResultBody queryArticleList(ArticleListQueryDto dto) throws GlobalErrorInfoException;

    /**
     * 医疗咨询详情内容查询
     * @param dto
     * @return
     */
    ResultBody queryArticleWithContent(ArticleDetailQueryDto dto) throws GlobalErrorInfoException;

}
