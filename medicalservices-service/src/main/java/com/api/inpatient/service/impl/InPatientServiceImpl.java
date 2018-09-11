package com.api.inpatient.service.impl;

import com.api.adapter.impl.HISCommonInterfaceTransAdapterImpl;
import com.api.async.reg.RegAsyncService;
import com.api.card.domain.Card;
import com.api.card.service.CardForPatService;
import com.api.constant.IConst;
import com.api.dto.card.QueryCardForPersonDto;
import com.api.dto.inpatient.*;
import com.api.inpatient.service.InPatientService;
import com.api.registered.dao.OrderMapper;
import com.api.registered.dao.OrderSettlementMapper;
import com.api.registered.domain.OrderEntity;
import com.api.registered.domain.OrderSettlementEntity;
import com.api.result.GlobalErrorInfoException;
import com.api.result.ResultBody;
import com.api.result.messageenum.GlobalErrorInfoEnum;
import com.api.result.messageenum.InpatientErrorInfoEnum;
import com.api.setting.HisSetting;
import com.api.util.DateUtil;
import com.api.util.RedissLockUtil;
import com.api.util.ReflectMapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderSettlementMapper orderSettlementMapper;
    @Autowired
    RegAsyncService regAsyncService;

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

    @Override
    public ResultBody createPrePayment(InpatientPrePaymentOrderDto dto) throws GlobalErrorInfoException {
        //step1 判断该患者是否绑卡
        QueryCardForPersonDto queryCardForPersonDto = new QueryCardForPersonDto();
        queryCardForPersonDto.setOut_platform_id(dto.getOut_platform_id());
        queryCardForPersonDto.setChannel(dto.getChannel());
        queryCardForPersonDto.setOrgCode(dto.getOrgCode());
        queryCardForPersonDto.setIdcard_no(dto.getIdcard_no());
        ResultBody cardBody = cardForPatService.queryCardInfoForPerson(queryCardForPersonDto);
        if(!cardBody.getCode().equals(GlobalErrorInfoEnum.SUCCESS.getCode())){
            return cardBody;
        }
        //创建card对象
        Card card = (Card) cardBody.getResult();
        //生成订单号
        String orderid = UUID.randomUUID().toString().replaceAll("-","");
        //step 2 记录预算结果(为了统一挂号和门诊缴费流程)
        OrderSettlementEntity orderSettlementEntity = new OrderSettlementEntity(orderid,card,dto,"1");
        orderSettlementMapper.insertSelective(orderSettlementEntity);
        //step 3 生成系统住院预交订单
        OrderEntity orderEntity = new OrderEntity(orderid,card,dto);
        orderEntity.setConsumeType("3");//表示住院预交订单
        orderEntity.setPayResult("01");//支付初始状态
        orderEntity.setRemarks("住院预交金订单");
        orderEntity.setHisResult("01");//his标识初始状态
        orderMapper.insertSelective(orderEntity);
        Map result = new HashMap();
        result.put("orderId",orderid);
        result.put("personAmt",orderSettlementEntity.getPersonamt());//个人自付金额
        result.put("tradebalance",orderSettlementEntity.getRegamt());//订单总金额
        result.put("time",DateUtil.formatDateToString(new Date(),DateUtil.FORMAT_FULL));//订单时间
        return new ResultBody(result);
    }

    @Override
    public ResultBody inpPrePayment(InpatientPrePaymentDto dto) throws GlobalErrorInfoException {
        String key = setting.getRedisLockInpatientAccount()+dto.getOrderId();
        try {
            //此处存在线程安全问题 该笔订单同一时间只能执行一次his结算操作
            RedissLockUtil.lock(key, TimeUnit.MINUTES,5);
            //step 1 查询订单信息 判断是否重复请求
            OrderEntity orderParam = new OrderEntity();
            orderParam.setOrderId(dto.getOrderId());
            OrderEntity order = orderMapper.queryLimitOne(orderParam);
            if(order == null){
                return new ResultBody(InpatientErrorInfoEnum.INPATIENT_ORDER_NODATA);
            }
            if("02".equals(order.getPayResult())&& "02".equals(order.getHisResult())){
                //标识重复提交请求 直接返回成功信息
                return new ResultBody();
            }
            //step 2根据订单查询订单结算信息
            OrderSettlementEntity orderSettlementEntity = new OrderSettlementEntity();
            orderSettlementEntity.setOrderId(dto.getOrderId());
            List<OrderSettlementEntity> pre_orders = orderSettlementMapper.queryByCond(orderSettlementEntity);
            if(pre_orders == null||pre_orders.size() == 0){
                return new ResultBody(InpatientErrorInfoEnum.INPATIENT_ACCOUNT_NOORDER);
            }else if(pre_orders.size()>1){
                return new ResultBody(InpatientErrorInfoEnum.INPATIENT_PAYRECORD_COUNTERROR);
            }
            OrderSettlementEntity pre_order = pre_orders.get(0);
            if(!"1".equals(pre_order.getStatus())){
                return new ResultBody(InpatientErrorInfoEnum.INPATIENT_PAYRECORD_DATAERROR);
            }
            //step 3 异步记录订单支付成功信息 更改订单支付状态
            regAsyncService.updateInpatientOrderPay(dto);
            //step 4 封装住院预交金参数 调用his
            InpatientPrePaymentHisDto inpatientPrePaymentHisDto = new InpatientPrePaymentHisDto();
            inpatientPrePaymentHisDto.setInterid(pre_order.getReceiptno());
            inpatientPrePaymentHisDto.setPatientName(pre_order.getPatName());
            inpatientPrePaymentHisDto.setPayChannel(dto.getAppCode());
            inpatientPrePaymentHisDto.setPayerName(dto.getPayerName());
            inpatientPrePaymentHisDto.setPayTradeno(dto.getPayTradeno());
            inpatientPrePaymentHisDto.setPayWay(dto.getPayway());
            inpatientPrePaymentHisDto.setPreAmt(dto.getPreAmt());
            ResultBody accountBody = serviceInvoke(ReflectMapUtil.beanToMap(inpatientPrePaymentHisDto));
            //his结算处理标识 默认成功
            String his_account_flag = "02";
            if(!IConst.HIS_SUCCESS.equals(accountBody.getCode())){
                //更改his处理标识
                his_account_flag = "03";
                //step 4 更改订单状态
                OrderEntity orderEntity = new OrderEntity();
                orderEntity.setOrderId(dto.getOrderId());
                orderEntity.setHisResult(his_account_flag);
                orderMapper.updateOrderByOrderId(orderEntity);
                //todo 异步退费
                Map refundMap = new HashMap();
                refundMap.put("orderId",dto.getOrderId());
                regAsyncService.saveRefund(refundMap);
                //表示his结算失败
                return new ResultBody(InpatientErrorInfoEnum.INPATIENT_ACCOUNT_ERROR);
            }
            //获取结算信息
            Object objAccountInfo = accountBody.getResult();
            Map accountInpatientInfo;
            if(objAccountInfo instanceof Map){
                accountInpatientInfo = (Map) objAccountInfo;
            }else{
                accountInpatientInfo = (Map) ((List)objAccountInfo).get(0);
            }
            //step 3 写入结算数据表
            pre_order.setRegreceipt(accountInpatientInfo.get("preReceipt")==null?null:accountInpatientInfo.get("preReceipt").toString());
            pre_order.setPersonamt("".equals(dto.getPreAmt())?new BigDecimal("0.00"):new BigDecimal(dto.getPreAmt()));
            pre_order.setAccountpayment(accountInpatientInfo.get("prePayment")==null||"".equals(accountInpatientInfo.get("prePayment").toString())?new BigDecimal("0.00"):new BigDecimal(accountInpatientInfo.get("prePayment").toString()));
            pre_order.setAccountbalance(accountInpatientInfo.get("balance")==null||"".equals(accountInpatientInfo.get("balance").toString())?new BigDecimal("0.00"):new BigDecimal(accountInpatientInfo.get("balance").toString()));
            pre_order.setPayway(dto.getPayway());
            pre_order.setPaychannel(dto.getAppCode());
            pre_order.setPaytradeno(dto.getPayTradeno());
            pre_order.setStatus("2");//结算
            orderSettlementMapper.insertSelective(pre_order);
            //step 4 更改订单状态
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderId(dto.getOrderId());
            orderEntity.setHisResult(his_account_flag);
            orderMapper.updateOrderByOrderId(orderEntity);
            return new ResultBody();
        }catch (Exception e){
            logger.error(e.toString());
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.SYS_ERROR);
        }finally {
            RedissLockUtil.unlock(key);
        }
    }

    private ResultBody serviceInvoke(Map param) throws GlobalErrorInfoException {
        return service.sendMsg(param, setting.getUrl());
    }
}
