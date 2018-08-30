package com.api.registered.service.impl;

import com.api.adapter.impl.HISCommonInterfaceTransAdapterImpl;
import com.api.card.domain.Card;
import com.api.card.service.CardForPatService;
import com.api.constant.IConst;
import com.api.dto.card.QueryCardForPersonDto;
import com.api.dto.register.RegAccountDto;
import com.api.dto.register.RegOrderSaveDto;
import com.api.dto.register.RegPreAccountDto;
import com.api.dto.register.RegRegisterDto;
import com.api.mq.model.Msg;
import com.api.mq.scenes.sendmq.MsgSender;
import com.api.registered.dao.OrderMapper;
import com.api.registered.dao.OrderSettlementMapper;
import com.api.registered.dao.RegistrationDetailMapper;
import com.api.registered.domain.OrderEntity;
import com.api.registered.domain.OrderSettlementEntity;
import com.api.registered.domain.RegistrationDetailEntity;
import com.api.registered.service.RegisteredService;
import com.api.result.GlobalErrorInfoException;
import com.api.result.ResultBody;
import com.api.result.messageenum.GlobalErrorInfoEnum;
import com.api.result.messageenum.RegisteredErrorInfoEnum;
import com.api.setting.HisSetting;
import com.api.util.ReflectMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 挂号服务实现
 */
@Transactional
@Configuration
@EnableConfigurationProperties(HisSetting.class)
@Service("registeredService")
public class RegisteredServiceImpl implements RegisteredService {
    @Autowired
    HisSetting setting;
    @Autowired
    HISCommonInterfaceTransAdapterImpl service;
    @Autowired
    private MsgSender msgSender;
    @Autowired
    private CardForPatService cardForPatService;
    @Autowired
    RegistrationDetailMapper registrationDetailMapper;
    @Autowired
    OrderSettlementMapper orderSettlementMapper;
    @Autowired
    OrderMapper orderMapper;

    @Override
    public ResultBody querySectionInformation(Map param) throws GlobalErrorInfoException {
        return serviceInvoke(param);
    }

    @Override
    public ResultBody queryDrInformation(Map param) throws GlobalErrorInfoException {
        return serviceInvoke(param);
    }

    @Override
    public ResultBody querySectionSourceInformation(Map param) throws GlobalErrorInfoException {
        return serviceInvoke(param);
    }
    @Override
    public ResultBody queryDrSourceInformation(Map param) throws GlobalErrorInfoException {
        return serviceInvoke(param);
    }
    @Override
    public ResultBody querySectionDrInformation(Map param) throws GlobalErrorInfoException {
        return serviceInvoke(param);
    }
    @Override
    public ResultBody queryObtainDrSection(Map param) throws GlobalErrorInfoException {
        return serviceInvoke(param);
    }
    @Override
    public ResultBody queryRegSource(Map param) throws GlobalErrorInfoException {
        return serviceInvoke(param);
    }
    @Override
    public ResultBody saveLockReg(RegOrderSaveDto dto) throws GlobalErrorInfoException {
        //step1 判断该患者是否绑卡
        QueryCardForPersonDto queryCardForPersonDto = new QueryCardForPersonDto(dto);
        ResultBody cardBody = cardForPatService.queryCardInfoForPerson(queryCardForPersonDto);
        if(!cardBody.getCode().equals(GlobalErrorInfoEnum.SUCCESS.getCode())){
            return cardBody;
        }
        //创建card对象
        Card card = (Card) cardBody.getResult();
        //生成订单号
        String orderid = UUID.randomUUID().toString().replaceAll("-","");
        //step2 判断是预约挂号还是当日
        if("1".equals(dto.getPreregFlag())){
            //step2.1表示预约挂号 那么先调用his锁号
            RegRegisterDto regRegisterDto = new RegRegisterDto(dto);
            regRegisterDto.setCardNo(card.getCardno());
            regRegisterDto.setCardType(card.getType());
            regRegisterDto.setPatId(card.getPatid());
            ResultBody lockBody = serviceInvoke(ReflectMapUtil.beanToMap(regRegisterDto));
            if(!IConst.HIS_SUCCESS.equals(lockBody.getCode())){
                //表示锁号失败
                return new ResultBody(RegisteredErrorInfoEnum.REG_LOCK_ERROR);
            }
            //获取锁号信息
            Object objRegInfo = lockBody.getResult();
            Map lockInfo;
            if(objRegInfo instanceof Map){
                lockInfo = (Map) objRegInfo;
            }else{
                lockInfo = (Map) ((List)objRegInfo).get(0);
            }
            //step 2.2 生成锁号记录单存储至数据库
            RegistrationDetailEntity registrationDetailEntity = new RegistrationDetailEntity(orderid,card,dto,lockInfo,"1");
            registrationDetailMapper.insertSelective(registrationDetailEntity);
            registrationDetailEntity.setCardtype(card.getType());//传递卡类型参数取消锁号使用
            dto.setSeqnum(lockInfo.get("yydjh").toString());//挂号时可空（若医院当日挂号只到号源，不分号序，则可以为空），预约时传预约登记号
            //step 2.3 启用10分钟后不支付自动取消锁号
            Msg msg = new Msg();
            msg.setCount(1);
            msg.setTime("10000");//测试10秒钟后调用取消锁号
            msg.setObj(registrationDetailEntity);
            msgSender.sendToMqForDelayCancelReg(msg);
        }
        //step 3 调用his预算接口
        RegPreAccountDto regPreAccountDto = new RegPreAccountDto(dto);
        regPreAccountDto.setCardNo(card.getCardno());
        regPreAccountDto.setCardType(card.getType());
        regPreAccountDto.setPatId(card.getPatid());
        ResultBody preBody = serviceInvoke(ReflectMapUtil.beanToMap(regPreAccountDto));
        if(!IConst.HIS_SUCCESS.equals(preBody.getCode())){
            //表示挂号预算失败
            return new ResultBody(RegisteredErrorInfoEnum.REG_PREACCOUNT_ERROR);
        }
        //获取预算信息
        Object objPreInfo = preBody.getResult();
        Map preRegInfo;
        if(objPreInfo instanceof Map){
            preRegInfo = (Map) objPreInfo;
        }else{
            preRegInfo = (Map) ((List)objPreInfo).get(0);
        }
        //step 4 记录预算结果
        OrderSettlementEntity orderSettlementEntity = new OrderSettlementEntity(orderid,card,dto,preRegInfo,"1");
        orderSettlementMapper.insertSelective(orderSettlementEntity);
        //step 5 生成系统挂号订单
        OrderEntity orderEntity = new OrderEntity(orderid,card,dto,preRegInfo,"1");
        orderEntity.setConsumeType("1");//表示挂号
        orderEntity.setPayResult("01");//支付初始状态
        orderEntity.setRemarks("挂号订单");
        orderEntity.setHisResult("01");//his标识初始状态
        orderMapper.insertSelective(orderEntity);
        Map result = new HashMap();
        result.put("orderId",orderid);
        result.put("tradeBalance",orderEntity.getTradeBalance());
        result.put("payWay",orderEntity.getPayway());
        result.put("payType",orderEntity.getPayType());
        return new ResultBody(result);
    }
    @Override
    public ResultBody regAccount(RegAccountDto dto) throws GlobalErrorInfoException {
        return null;
    }
    private ResultBody serviceInvoke(Map param) throws GlobalErrorInfoException {
        return service.sendMsg(param, setting.getUrl());
    }

}
