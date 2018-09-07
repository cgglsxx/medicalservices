package com.api.clinic.service.impl;

import com.api.adapter.impl.HISCommonInterfaceTransAdapterImpl;
import com.api.async.reg.RegAsyncService;
import com.api.card.domain.Card;
import com.api.card.service.CardForPatService;
import com.api.clinic.service.ClinicService;
import com.api.constant.IConst;
import com.api.dto.card.QueryCardForPersonDto;
import com.api.dto.clinic.*;
import com.api.registered.dao.OrderMapper;
import com.api.registered.dao.OrderSettlementMapper;
import com.api.registered.domain.OrderEntity;
import com.api.registered.domain.OrderSettlementEntity;
import com.api.result.GlobalErrorInfoException;
import com.api.result.ResultBody;
import com.api.result.messageenum.ClinicErrorInfoEnum;
import com.api.result.messageenum.GlobalErrorInfoEnum;
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
 * 门诊缴费服务实现
 */
@Transactional
@Configuration
@EnableConfigurationProperties(HisSetting.class)
@Service("clinicService")
public class ClinicServiceImpl implements ClinicService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    HisSetting setting;
    @Autowired
    HISCommonInterfaceTransAdapterImpl service;
    @Autowired
    private CardForPatService cardForPatService;
    @Autowired
    OrderSettlementMapper orderSettlementMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    RegAsyncService regAsyncService;
    @Override
    public ResultBody queryClinicBillInfo(ClinicBillInfoQueryDto dto) throws GlobalErrorInfoException {
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
        ClinicBillInfoQueryHisDto clinicBillInfoQueryHisDto = new ClinicBillInfoQueryHisDto(dto);
        clinicBillInfoQueryHisDto.setCardNo(card.getCardno());
        clinicBillInfoQueryHisDto.setCardType(card.getType());
        clinicBillInfoQueryHisDto.setPatId(card.getPatid());
        ResultBody resultBody = serviceInvoke(ReflectMapUtil.beanToMap(clinicBillInfoQueryHisDto));
        return resultBody;
    }

    @Override
    public ResultBody queryClinicBillDetail(ClinicBillDetailQueryDto dto) throws GlobalErrorInfoException {
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
        ClinicBillDetailQueryHisDto clinicBillDetailQueryHisDto = new ClinicBillDetailQueryHisDto(dto);
        clinicBillDetailQueryHisDto.setCardNo(card.getCardno());
        clinicBillDetailQueryHisDto.setCardType(card.getType());
        clinicBillDetailQueryHisDto.setPatId(card.getPatid());
        ResultBody resultBody = serviceInvoke(ReflectMapUtil.beanToMap(clinicBillDetailQueryHisDto));
        return resultBody;
    }

    @Override
    public ResultBody clinicPreAccount(ClinicPreAccountDto dto) throws GlobalErrorInfoException {
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
        //生成订单号
        String orderid = UUID.randomUUID().toString().replaceAll("-","");
        //step 2 调用his门诊预算接口
        ClinicPreAccountHisDto clinicPreAccountHisDto = new ClinicPreAccountHisDto(dto);
        clinicPreAccountHisDto.setCardNo(card.getCardno());
        clinicPreAccountHisDto.setCardType(card.getType());
        clinicPreAccountHisDto.setPatId(card.getPatid());
        ResultBody preBody = serviceInvoke(ReflectMapUtil.beanToMap(clinicPreAccountHisDto));
        if(!IConst.HIS_SUCCESS.equals(preBody.getCode())){
            //表示挂号预算失败
            return new ResultBody(ClinicErrorInfoEnum.CLINIC_PREACCOUNT_ERROR);
        }
        //获取预算信息
        Object objClinicInfo = preBody.getResult();
        Map preClinicInfo;
        if(objClinicInfo instanceof Map){
            preClinicInfo = (Map) objClinicInfo;
        }else{
            preClinicInfo = (Map) ((List)objClinicInfo).get(0);
        }
        //step 4 记录预算结果
        OrderSettlementEntity orderSettlementEntity = new OrderSettlementEntity(orderid,card,dto,preClinicInfo,"1");
        String personAmtList = preClinicInfo.get("personAmtList")==null?"0.00":preClinicInfo.get("personAmtList").toString();
        //计算自付总金额
        String[] personAmtListarr = personAmtList.split("|");
        BigDecimal personAmt = new BigDecimal("0.00");
        for(int i=0,j=personAmtListarr.length;i<j;i++){
            personAmt.add(new BigDecimal(personAmtListarr[i]));
        }
        orderSettlementEntity.setPersonamt(personAmt);
        orderSettlementMapper.insertSelective(orderSettlementEntity);
        //step 5 生成系统门诊缴费订单
        OrderEntity orderEntity = new OrderEntity(orderid,card,dto,preClinicInfo);
        orderEntity.setConsumeType("2");//表示门诊缴费订单
        orderEntity.setPayResult("01");//支付初始状态
        orderEntity.setRemarks("门诊缴费订单");
        orderEntity.setHisResult("01");//his标识初始状态
        orderMapper.insertSelective(orderEntity);
        Map result = new HashMap();
        result.put("orderId",orderid);
        result.put("personAmt",personAmt);//个人自付金额
        result.put("discountsAmt",orderSettlementEntity.getDiscountsamt());//医院优惠金额
        result.put("tradebalance",orderSettlementEntity.getRegamt());//订单总金额
        result.put("time",DateUtil.formatDateToString(new Date(),DateUtil.FORMAT_FULL));//订单时间
        return new ResultBody(result);
    }

    @Override
    public ResultBody clinicAccount(ClinicAccountDto dto) throws GlobalErrorInfoException {
        String key = setting.getRedisLockClinicAccount()+dto.getOrderId();
        try {
            //此处存在线程安全问题 该笔订单同一时间只能执行一次his结算操作
            RedissLockUtil.lock(key, TimeUnit.MINUTES,5);
            //step 1 查询订单信息 判断是否重复请求
            OrderEntity orderParam = new OrderEntity();
            orderParam.setOrderId(dto.getOrderId());
            OrderEntity order = orderMapper.queryLimitOne(orderParam);
            if(order == null){
                return new ResultBody(ClinicErrorInfoEnum.CLINIC_ORDER_NODATA);
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
                return new ResultBody(ClinicErrorInfoEnum.CLINIC_ACCOUNT_NOORDER);
            }else if(pre_orders.size()>1){
                return new ResultBody(ClinicErrorInfoEnum.CLINIC_PAYRECORD_COUNTERROR);
            }
            OrderSettlementEntity pre_order = pre_orders.get(0);
            if(!"1".equals(pre_order.getStatus())){
                return new ResultBody(ClinicErrorInfoEnum.CLINIC_PAYRECORD_DATAERROR);
            }
            //step 3 异步记录订单支付成功信息 更改订单支付状态
            regAsyncService.updateClinicOrderPay(dto);
            //step 4 封装结算参数 调用结算
            ClinicAccountHisDto clinicAccountHisDto = new ClinicAccountHisDto();
            clinicAccountHisDto.setPatId(pre_order.getPatid());
            clinicAccountHisDto.setCardNo(pre_order.getCardno());
            clinicAccountHisDto.setCardType(pre_order.getCardtype());
            clinicAccountHisDto.setStatementNo(pre_order.getReceiptno());
            clinicAccountHisDto.setReceiptList(pre_order.getReceiptlist());
            clinicAccountHisDto.setChargeTypeList(pre_order.getChargetypelist());
            clinicAccountHisDto.setPersonAmtList(pre_order.getPersonamtlist());
            clinicAccountHisDto.setChargeTamt(pre_order.getRegamt().toString());
            clinicAccountHisDto.setPayMoney(dto.getPayMoney());
            clinicAccountHisDto.setDiscountsAmt(pre_order.getDiscountsamt().toString());
            clinicAccountHisDto.setPersonAmt(dto.getPersonAmt());
            clinicAccountHisDto.setPayWay(dto.getPayway());
            clinicAccountHisDto.setPayChannel(dto.getAppCode());
            clinicAccountHisDto.setPayTradeno(dto.getPayTradeno());
            clinicAccountHisDto.setWhetherDed(pre_order.getWhetherded());
            clinicAccountHisDto.setWhetherSet(pre_order.getWhetherset());
            clinicAccountHisDto.setHospitalcardNo(pre_order.getHospitalcardno());
            clinicAccountHisDto.setPassword(pre_order.getPassword());
            ResultBody accountBody = serviceInvoke(ReflectMapUtil.beanToMap(clinicAccountHisDto));
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
                return new ResultBody(ClinicErrorInfoEnum.CLINIC_ACCOUNT_ERROR);
            }
            //获取结算信息
            Object objAccountInfo = accountBody.getResult();
            Map accountClinicInfo;
            if(objAccountInfo instanceof Map){
                accountClinicInfo = (Map) objAccountInfo;
            }else{
                accountClinicInfo = (Map) ((List)objAccountInfo).get(0);
            }
            //step 3 写入结算数据表
            pre_order.setPyckjh(accountClinicInfo.get("pyckjh")==null?null:accountClinicInfo.get("pyckjh").toString());
            pre_order.setFyckjh(accountClinicInfo.get("fyckjh")==null?null:accountClinicInfo.get("fyckjh").toString());
            pre_order.setRegreceipt(accountClinicInfo.get("sjhInfo")==null?null:accountClinicInfo.get("sjhInfo").toString());
            pre_order.setPersonamt("".equals(dto.getPersonAmt())?new BigDecimal("0.00"):new BigDecimal(dto.getPersonAmt()));
            pre_order.setPaymoney("".equals(dto.getPayMoney())?new BigDecimal("0.00"):new BigDecimal(dto.getPayMoney()));
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
