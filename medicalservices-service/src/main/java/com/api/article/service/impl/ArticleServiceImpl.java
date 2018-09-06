package com.api.article.service.impl;


import com.api.article.dao.ArticleCategoryMapper;
import com.api.article.dao.ArticleMapper;
import com.api.article.domain.ArticleCategoryEntity;
import com.api.article.domain.ArticleEntity;
import com.api.article.service.ArticleService;
import com.api.dto.article.ArticleDetailQueryDto;
import com.api.dto.article.ArticleListQueryDto;
import com.api.dto.article.CatesQueryDto;
import com.api.result.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 医疗咨讯
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleCategoryMapper articleCategoryMapper;


    @Override
    public ResultBody queryCates(CatesQueryDto dto)  {
        ResultBody resultBody = new ResultBody();
        ArticleCategoryEntity articleCategoryEntity = new ArticleCategoryEntity();
        if(!"0".equals(dto.getCate_type())){
            //表示不是查询全部类型咨讯分类
            articleCategoryEntity.setCateType(Integer.parseInt(dto.getCate_type()));//设置查询类型
            articleCategoryEntity.setStatus(1);//表示启用
        }
        List<ArticleCategoryEntity> articleCategoryEntityList = articleCategoryMapper.queryByCond(articleCategoryEntity);
        resultBody.setResult(articleCategoryEntityList);
        return resultBody;
    }

    @Override
    public ResultBody queryArticleList(ArticleListQueryDto dto) {
        ResultBody resultBody = new ResultBody();
        ArticleEntity articleEntity = new ArticleEntity();
        if(dto.getCate_id() != null){
            articleEntity.setCateId(Integer.parseInt(dto.getCate_id()));
        }
        articleEntity.setKeywords(dto.getKeywords());
        articleEntity.setTitle(dto.getTitle());
        articleEntity.setStatus(2);//查询审核通过的文章
        List<ArticleEntity> articleEntityList = articleMapper.queryByCond(articleEntity);
        resultBody.setResult(articleEntityList);
        return resultBody;
    }

    @Override
    public ResultBody queryArticleWithContent(ArticleDetailQueryDto dto)  {
        ResultBody resultBody = new ResultBody();
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setArticleId(Integer.parseInt(dto.getArticle_id()));
        articleEntity.setStatus(2);//查询审核通过的文章
        ArticleEntity result = articleMapper.queryLimitOne(articleEntity);
        resultBody.setResult(result);
        return resultBody;
    }
}

