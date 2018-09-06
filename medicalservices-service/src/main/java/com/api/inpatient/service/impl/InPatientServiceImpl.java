package com.api.inpatient.service.impl;

import com.api.adapter.impl.HISCommonInterfaceTransAdapterImpl;
import com.api.card.domain.Card;
import com.api.card.service.CardForPatService;
import com.api.dto.card.QueryCardForPersonDto;
import com.api.dto.inpatient.*;
import com.api.inpatient.InPatientService;
import com.api.result.GlobalErrorInfoException;
import com.api.result.ResultBody;
import com.api.result.messageenum.GlobalErrorInfoEnum;
import com.api.setting.HisSetting;
import com.api.util.ReflectMapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
/**
 * 住院服务实现
 */
@Transactional
@Configuration
@EnableConfigurationProperties(HisSetting.class)
@Service("inPatientService")
public class InPatientServiceImpl implements InPatientService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    HisSetting setting;
    @Autowired
    HISCommonInterfaceTransAdapterImpl service;
    @Autowired
    private CardForPatService cardForPatService;

    @Override
    public ResultBody queryInpatientInfo(InpatientInfoQueryDto dto) throws GlobalErrorInfoException {
        //step1 判断该患者是否绑卡
        QueryCardForPersonDto queryCardForPersonDto = new QueryCardForPersonDto();
        queryCardForPersonDto.setOrgCode(dto.getOrgCode());
        queryCardForPersonDto.setIdcard_no(dto.getIdcard_no());
        queryCardForPersonDto.setChannel(dto.getChannel());
        queryCardForPersonDto.setOut_platform_id(dto.getOut_platform_id());
        ResultBody cardBody = cardForPatService.queryCardInfoForPerson(queryCardForPersonDto);
        if(!cardBody.getCode().equals(GlobalErrorInfoEnum.SUCCESS.getCode())){
            return cardBody;
        }
        //创建card对象
        Card card = (Card) cardBody.getResult();
        InpatientInfoQueryHisDto inpatientInfoQueryHisDto = new InpatientInfoQueryHisDto();
        inpatientInfoQueryHisDto.setCardNo(card.getCardno());
        inpatientInfoQueryHisDto.setCardType(card.getType());
        inpatientInfoQueryHisDto.setPatientName(card.getPat_name());
        inpatientInfoQueryHisDto.setCertId(card.getIdcard_no());
        inpatientInfoQueryHisDto.setPatId(card.getPatid());
        inpatientInfoQueryHisDto.setPhone(card.getMobile());
        ResultBody resultBody = serviceInvoke(ReflectMapUtil.beanToMap(inpatientInfoQueryHisDto));
        return resultBody;
    }

    @Override
    public ResultBody queryInpCost(InpatientCostQueryDto dto) throws GlobalErrorInfoException {
        //step1 判断该患者是否绑卡
        QueryCardForPersonDto queryCardForPersonDto = new QueryCardForPersonDto();
        queryCardForPersonDto.setOrgCode(dto.getOrgCode());
        queryCardForPersonDto.setIdcard_no(dto.getIdcard_no());
        queryCardForPersonDto.setChannel(dto.getChannel());
        queryCardForPersonDto.setOut_platform_id(dto.getOut_platform_id());
        ResultBody cardBody = cardForPatService.queryCardInfoForPerson(queryCardForPersonDto);
        if(!cardBody.getCode().equals(GlobalErrorInfoEnum.SUCCESS.getCode())){
            return cardBody;
        }
        InpatientCostQueryHisDto inpatientCostQueryHisDto = new InpatientCostQueryHisDto(dto);
        ResultBody resultBody = serviceInvoke(ReflectMapUtil.beanToMap(inpatientCostQueryHisDto));
        return resultBody;
    }

    @Override
    public ResultBody queryPrePayment(InpatientPrePaymentQueryDto dto) throws GlobalErrorInfoException {
        //step1 判断该患者是否绑卡
        QueryCardForPersonDto queryCardForPersonDto = new QueryCardForPersonDto();
        queryCardForPersonDto.setOrgCode(dto.getOrgCode());
        queryCardForPersonDto.setIdcard_no(dto.getIdcard_no());
        queryCardForPersonDto.setChannel(dto.getChannel());
        queryCardForPersonDto.setOut_platform_id(dto.getOut_platform_id());
        ResultBody cardBody = cardForPatService.queryCardInfoForPerson(queryCardForPersonDto);
        if(!cardBody.getCode().equals(GlobalErrorInfoEnum.SUCCESS.getCode())){
            return cardBody;
        }
        InpatientPrePaymentQueryHisDto inpatientPrePaymentQueryHisDto = new InpatientPrePaymentQueryHisDto(dto);
        ResultBody resultBody = serviceInvoke(ReflectMapUtil.beanToMap(inpatientPrePaymentQueryHisDto));
        return resultBody;
    }

    @Override
    public ResultBody inpHistoryRecord(InpatientRecordsQueryDto dto) throws GlobalErrorInfoException {
        //step1 判断该患者是否绑卡
        QueryCardForPersonDto queryCardForPersonDto = new QueryCardForPersonDto();
        queryCardForPersonDto.setOrgCode(dto.getOrgCode());
        queryCardForPersonDto.setIdcard_no(dto.getIdcard_no());
        queryCardForPersonDto.setChannel(dto.getChannel());
        queryCardForPersonDto.setOut_platform_id(dto.getOut_platform_id());
        ResultBody cardBody = cardForPatService.queryCardInfoForPerson(queryCardForPersonDto);
        if(!cardBody.getCode().equals(GlobalErrorInfoEnum.SUCCESS.getCode())){
            return cardBody;
        }
        //创建card对象
        Card card = (Card) cardBody.getResult();
        InpatientRecordsQueryHisDto inpatientRecordsQueryHisDto = new InpatientRecordsQueryHisDto(dto);
        inpatientRecordsQueryHisDto.setPatId(card.getPatid());
        ResultBody resultBody = serviceInvoke(ReflectMapUtil.beanToMap(inpatientRecordsQueryHisDto));
        return resultBody;
    }
    private ResultBody serviceInvoke(Map param) throws GlobalErrorInfoException {
        return service.sendMsg(param, setting.getUrl());
    }
}
