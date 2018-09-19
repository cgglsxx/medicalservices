package com.api.symptoms.service.impl;


import com.api.dto.symptoms.BodysQueryDto;
import com.api.dto.symptoms.SymptomsDetailQueryDto;
import com.api.dto.symptoms.SymptomsListQueryDto;
import com.api.result.ResultBody;
import com.api.symptoms.dao.BodyCategoryMapper;
import com.api.symptoms.dao.SymptomsMapper;
import com.api.symptoms.domain.BodyCategoryEntity;
import com.api.symptoms.domain.SymptomsEntity;
import com.api.symptoms.service.SymptomsService;
import com.api.util.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


/**
 * 医疗咨讯
 */
@Service("symptomsService")
public class SymptomsServiceImpl implements SymptomsService {
    @Autowired
    BodyCategoryMapper bodyCategoryMapper;
    @Autowired
    SymptomsMapper symptomsMapper;


    @Override
    public ResultBody queryBodys(BodysQueryDto dto)  {
        ResultBody resultBody = new ResultBody();
        BodyCategoryEntity bodyCategoryEntity = new BodyCategoryEntity();
        bodyCategoryEntity.setStatus(1);//表示启用
        List bodyCategoryEntityList = bodyCategoryMapper.queryByCond(bodyCategoryEntity);
        List treeList = TreeUtil.getTreeList(bodyCategoryEntityList, dto.getParent_id(), new HashMap(), "children");
        resultBody.setResult(treeList);
        return resultBody;
    }

    @Override
    public ResultBody querySymptomsList(SymptomsListQueryDto dto) {
        ResultBody resultBody = new ResultBody();
        SymptomsEntity symptomsEntity = new SymptomsEntity();
        symptomsEntity.setCateId(dto.getCate_id());
        symptomsEntity.setTitle(dto.getTitle());
        symptomsEntity.setStatus("1");//启用
        List<SymptomsEntity> symptomsEntityList = symptomsMapper.queryByCond(symptomsEntity);
        resultBody.setResult(symptomsEntityList);
        return resultBody;
    }

    @Override
    public ResultBody querySymptomsWithContent(SymptomsDetailQueryDto dto)  {
        ResultBody resultBody = new ResultBody();
        SymptomsEntity symptomsEntity = new SymptomsEntity();
        symptomsEntity.setSymptomsId(Integer.parseInt(dto.getSymptoms_id()));
        symptomsEntity.setStatus("1");//启用
        SymptomsEntity result = symptomsMapper.queryLimitOne(symptomsEntity);
        resultBody.setResult(result);
        return resultBody;
    }
}

