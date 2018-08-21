package com.api.card.service.impl;

import com.api.account.dao.AccountMapper;
import com.api.account.domain.Account;
import com.api.account.domain.AccountKey;
import com.api.adapter.impl.HISCommonInterfaceTransAdapterImpl;
import com.api.card.dao.CardMapper;
import com.api.card.domain.Card;
import com.api.card.domain.CardKey;
import com.api.card.service.CardForPatService;
import com.api.constant.IConst;
import com.api.dto.card.*;
import com.api.result.GlobalErrorInfoException;
import com.api.result.ResultBody;
import com.api.result.messageenum.CardErrorInfoEnum;
import com.api.result.messageenum.GlobalErrorInfoEnum;
import com.api.setting.HisSetting;
import com.api.util.RedissLockUtil;
import com.api.util.ReflectMapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 就诊卡服务实现
 */
@Configuration
@EnableConfigurationProperties(HisSetting.class)
@Service("cardForPatService")
public class CardForPatServiceImpl implements CardForPatService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    HisSetting setting;
    @Autowired
    HISCommonInterfaceTransAdapterImpl service;
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    CardMapper cardMapper;
    @Override
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public ResultBody saveBooking(BookingDto dto) throws GlobalErrorInfoException {
        String key = setting.getRedisLockCardPre()+dto.getOut_platform_id()+dto.getChannel();
        try{
            //此处存在线程安全问题 锁第三方id的此次无卡操作（该接口锁和绑卡锁互斥 限制用户同一时刻只能做其中一个操作）
            RedissLockUtil.lock(key, TimeUnit.MINUTES,5);
            //检查绑卡条件是否满足
            String channel = dto.getChannel();
            String out_platform_id = dto.getOut_platform_id();
            String agency_num = dto.getOrgCode();
            String idcard_no = dto.getIdcard_no();
            ResultBody resultBody = checkBindCondition(out_platform_id,channel,idcard_no,agency_num);
            if(!resultBody.getCode().equals(GlobalErrorInfoEnum.SUCCESS.getCode())){
                return resultBody;
            }
            //查询his中是否已经建立档案
            QueryBookingDto queryBookingDto = new QueryBookingDto(dto);
            ResultBody ptresultBody = serviceInvoke(ReflectMapUtil.beanToMap(queryBookingDto));
            if(IConst.HIS_SUCCESS.equals(ptresultBody.getCode())){
                //表示查询到病人信息
                return new ResultBody(CardErrorInfoEnum.CARD_PATINFO_EXIST);
            }
            //调用his建档 获取用户的注册信息封装写入卡管理
            dto.setServiceId("createPatientInfo");//调用病人建档服务名设置
            ResultBody hisresult = serviceInvoke(ReflectMapUtil.beanToMap(dto));
            List hisList = (List) hisresult.getResult();
            Map hisMap = (Map) hisList.get(0);
            Card cardInsert = new Card();
            cardInsert.setPat_name(dto.getPat_name());
            cardInsert.setAddress(dto.getAddress());
            cardInsert.setCardno(hisMap.get("cardNo").toString());
            cardInsert.setPatid(hisMap.get("patId").toString());
            cardInsert.setRelationship(dto.getRelationship());
            cardInsert.setStatus("1");
            cardInsert.setType("1");
            cardInsert.setMobile(dto.getMobile());
            cardInsert.setIdcard_no(dto.getIdcard_no());
            cardInsert.setAgency_num(agency_num);
            cardInsert.setOut_platform_id(dto.getOut_platform_id());
            cardInsert.setChannel(dto.getChannel());
            cardMapper.insert(cardInsert);
            return new ResultBody();
        }catch (Exception e){
            logger.error(e.toString());
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.DB_OPERATION_ERROR);
        }finally {
            RedissLockUtil.unlock(key);
        }
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public ResultBody saveBindCard(BindCardDto dto) throws GlobalErrorInfoException {
        String key = setting.getRedisLockCardPre()+dto.getOut_platform_id()+dto.getChannel();
        try{
            //此处存在线程安全问题 锁第三方id的此次绑卡操作(该接口锁和无卡注册锁互斥 限制用户同一时刻只能做其中一个操作)
            RedissLockUtil.lock(key, TimeUnit.MINUTES,5);
            //检查绑卡条件是否满足
            String channel = dto.getChannel();
            String out_platform_id = dto.getOut_platform_id();
            String agency_num = dto.getOrgCode();
            String idcard_no = dto.getIdcard_no();
            ResultBody resultBody = checkBindCondition(out_platform_id,channel,idcard_no,agency_num);
            if(!resultBody.getCode().equals(GlobalErrorInfoEnum.SUCCESS.getCode())){
                return new ResultBody((CardErrorInfoEnum.CARD_ALREADY_BIND));
            }
            //先调用病人信息查询获取patid
            QueryBookingDto queryBookingDto = new QueryBookingDto(dto);
            ResultBody ptresult = serviceInvoke(ReflectMapUtil.beanToMap(queryBookingDto));
            if(!IConst.HIS_SUCCESS.equals(ptresult.getCode())){
                //表示该病人还未建档
                return new ResultBody(CardErrorInfoEnum.CARD_NO_PATINFO_EXIST);
            }
            //获取用户信息
            Object objPtInfo = ptresult.getResult();
            Map ptInfo;
            if(objPtInfo instanceof Map){
                ptInfo = (Map) objPtInfo;
            }else{
                ptInfo = (Map) ((List)objPtInfo).get(0);
            }
            //获取有效身份介质
            Object objMedium = ptInfo.get("mediumInfo");
            if(objMedium instanceof Map){
                Map ptMediumInfo = (Map) objMedium;
                String cardType = ptMediumInfo.get("cardType").toString();
                String cardNo = ptMediumInfo.get("cardNo").toString();
                if(!(cardType.equals(dto.getCardType())&&cardNo.equals(dto.getCardNo()))){
                    return new ResultBody(CardErrorInfoEnum.CARD_INFO_ERROR);
                }
            }else{
                List ptMediumInfoList = (List)objMedium;
                Map medium;
                Boolean flag = false;//表示验证未通过
                for(Object obj:ptMediumInfoList){
                    medium = (Map) obj;
                    String cardType = medium.get("cardType").toString();
                    String cardNo = medium.get("cardNo").toString();
                    if(cardType.equals(dto.getCardType()) && cardNo.equals(dto.getCardNo())){
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    return new ResultBody(CardErrorInfoEnum.CARD_INFO_ERROR);
                }
            }
            //todo 调用病人卡号查询 获取用户绑卡信息对比验证后写入卡管理
//            dto.setServiceId("queryPatientCardNo");
//            ResultBody hisresult = serviceInvoke(ReflectMapUtil.beanToMap(dto));
            Card cardInsert = new Card();
            cardInsert.setPat_name(dto.getPat_name());
            cardInsert.setAddress(dto.getAddress());
            cardInsert.setCardno(dto.getCardNo());
            cardInsert.setPatid(ptInfo.get("patId").toString());
            cardInsert.setRelationship(dto.getRelationship());
            cardInsert.setStatus("1");
            cardInsert.setType(dto.getCardType());
            cardInsert.setMobile(dto.getMobile());
            cardInsert.setIdcard_no(dto.getIdcard_no());
            cardInsert.setAgency_num(agency_num);
            cardInsert.setOut_platform_id(dto.getOut_platform_id());
            cardInsert.setChannel(dto.getChannel());
            cardMapper.insert(cardInsert);
            return new ResultBody();
        }catch (Exception e){
            logger.error(e.toString());
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.DB_OPERATION_ERROR);
        }finally {
            RedissLockUtil.unlock(key);
        }
    }
    @Override
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public ResultBody updateUnBindCard(UnBindCardDto dto) throws GlobalErrorInfoException {
        CardKey cardDelete = new CardKey();
        cardDelete.setIdcard_no(dto.getIdcard_no());
        cardDelete.setAgency_num(dto.getOrgCode());
        cardDelete.setOut_platform_id(dto.getOut_platform_id());
        cardDelete.setChannel(dto.getChannel());
        int count = cardMapper.deleteByPrimaryKey(cardDelete);
        if(count == 0){
            return new ResultBody(CardErrorInfoEnum.CARD_UNBIND_NODATA);
        }else if(count == 1){
            return new ResultBody();
        }else{
            throw new GlobalErrorInfoException(CardErrorInfoEnum.CARD_UNBIND_DATA_ERROR);
        }
    }

    @Override
    public ResultBody queryCardInfo(QueryCardDto dto) throws GlobalErrorInfoException {
        CardKey cardQuery = new CardKey();
        cardQuery.setAgency_num(dto.getOrgCode());
        cardQuery.setOut_platform_id(dto.getOut_platform_id());
        cardQuery.setChannel(dto.getChannel());
        List<Card> cards = cardMapper.selectByAccount(cardQuery);
        return new ResultBody(cards);
    }
    private ResultBody checkBindCondition(String out_platform_id,String channel,String idcard_no,String agency_num){
        //step1 先判断该账号是否绑定就诊卡超过5条
        CardKey cardQuery = new CardKey();
        cardQuery.setChannel(channel);
        cardQuery.setAgency_num(agency_num);
        cardQuery.setOut_platform_id(out_platform_id);
        List<Card> cards = cardMapper.selectByAccount(cardQuery);
        if(cards != null && cards.size() >= 5){
            return new ResultBody(CardErrorInfoEnum.CARD_BIND_CEILING);
        }
        //step2 判断该用户是否绑定就诊卡
        for(Object card:cards){
            Card cardObj = (Card) card;
            if(cardObj.getIdcard_no().equals(idcard_no)){
                //表示该用户已经绑定就诊卡
                return new ResultBody(CardErrorInfoEnum.CARD_REGISTERED_REPEAT);
            }
        }
        //step3 判断该三方账号是否在平台中存在
        AccountKey accuntQuery = new AccountKey();
        accuntQuery.setOut_platform_id(out_platform_id);
        accuntQuery.setChannel(channel);
        Account account = accountMapper.selectByPrimaryKey(accuntQuery);
        if(account == null){
            //插入该账号信息
            Account accuntInsert = new Account();
            accuntInsert.setChannel(channel);
            accuntInsert.setOut_platform_id(out_platform_id);
            accountMapper.insert(accuntInsert);
        }
        return new ResultBody();
    }
    private ResultBody serviceInvoke(Map param) throws GlobalErrorInfoException {
        return service.sendMsg(param, setting.getUrl());
    }
}

