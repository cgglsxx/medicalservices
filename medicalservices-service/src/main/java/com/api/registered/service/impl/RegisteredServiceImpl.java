package com.api.registered.service.impl;

import com.api.adapter.impl.HISCommonInterfaceTransAdapterImpl;
import com.api.async.reg.RegAsyncService;
import com.api.card.domain.Card;
import com.api.card.service.CardForPatService;
import com.api.constant.IConst;
import com.api.dto.card.QueryCardForPersonDto;
import com.api.dto.register.*;
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
import com.api.util.DateUtil;
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

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 挂号服务实现
 */
@Transactional
@Configuration
@EnableConfigurationProperties(HisSetting.class)
@Service("registeredService")
public class RegisteredServiceImpl implements RegisteredService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
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
    @Autowired
    RegAsyncService regAsyncService;

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
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
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
            msg.setTime("60000");//测试10秒钟后调用取消锁号
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
        result.put("personAmt",orderSettlementEntity.getPersonamt());//个人自付金额
        result.put("discountsAmt",orderSettlementEntity.getDiscountsamt());//医院优惠金额
        result.put("tradebalance",orderSettlementEntity.getRegamt());//订单总金额
        result.put("time",DateUtil.formatDateToString(new Date(),DateUtil.FORMAT_FULL));//订单时间
        return new ResultBody(result);
    }
    @Override
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public ResultBody regAccount(RegAccountDto dto) throws GlobalErrorInfoException {
        String key = setting.getRedisLockRegAccount()+dto.getOrderId();
        try {
            //此处存在线程安全问题 该笔订单同一时间只能执行一次his结算操作
            RedissLockUtil.lock(key, TimeUnit.MINUTES,5);
            //step 1 查询订单信息 判断是否重复请求
            OrderEntity orderParam = new OrderEntity();
            orderParam.setOrderId(dto.getOrderId());
            OrderEntity order = orderMapper.queryLimitOne(orderParam);
            if(order == null){
                return new ResultBody(RegisteredErrorInfoEnum.REG_ORDER_NODATA);
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
                return new ResultBody(RegisteredErrorInfoEnum.REG_ACCOUNT_NOORDER);
            }else if(pre_orders.size()>1){
                return new ResultBody(RegisteredErrorInfoEnum.REG_PAYRECORD_COUNTERROR);
            }
            OrderSettlementEntity pre_order = pre_orders.get(0);
            if(!"1".equals(pre_order.getStatus())){
                return new ResultBody(RegisteredErrorInfoEnum.REG_PAYRECORD_DATAERROR);
            }
            //step 3 异步记录订单支付成功信息 更改订单支付状态
            regAsyncService.updateRegOrderPay(dto);
            //step 4 封装结算参数 调用结算
            RegAccountOptionDto accountOptionDto = new RegAccountOptionDto();
            accountOptionDto.setPatId(pre_order.getPatid());
            accountOptionDto.setCardNo(pre_order.getCardno());
            accountOptionDto.setCardType(pre_order.getCardtype());
            accountOptionDto.setPreregFlag(pre_order.getPreregflag());
            accountOptionDto.setRegType(pre_order.getRegtype());
            accountOptionDto.setDeptId(pre_order.getDeptid());
            accountOptionDto.setDrId(pre_order.getDrid());
            accountOptionDto.setTscid(pre_order.getTscid());
            accountOptionDto.setTscdate(DateUtil.formatDateToString(pre_order.getTscdate(),DateUtil.FORMAT_DEFAULT));
            accountOptionDto.setDaySection(pre_order.getDaysection());
            accountOptionDto.setRegId(pre_order.getRegid());
            accountOptionDto.setSeqnum(pre_order.getSeqnum());
            accountOptionDto.setDelayPay("0");
            accountOptionDto.setRegAmt(pre_order.getRegamt().toString());
            accountOptionDto.setPersonAmt(dto.getPersonAmt());
            accountOptionDto.setPayMoney(dto.getPayMoney());
            accountOptionDto.setPayWay(dto.getPayway());
            accountOptionDto.setPayChannel(dto.getAppCode());
            accountOptionDto.setPayTradeno(dto.getPayTradeno());
            accountOptionDto.setReceiptNo(pre_order.getReceiptno());
            accountOptionDto.setWhetherDed(pre_order.getWhetherded());
            accountOptionDto.setWhetherSet(pre_order.getWhetherset());
            accountOptionDto.setHospitalcardNo(pre_order.getHospitalcardno());
            accountOptionDto.setPassword(pre_order.getPassword());
            ResultBody accountBody = serviceInvoke(ReflectMapUtil.beanToMap(accountOptionDto));
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
                return new ResultBody(RegisteredErrorInfoEnum.REG_ACCOUNT_ERROR);
            }
            //获取结算信息
            Object objAccountInfo = accountBody.getResult();
            Map accountRegInfo;
            if(objAccountInfo instanceof Map){
                accountRegInfo = (Map) objAccountInfo;
            }else{
                accountRegInfo = (Map) ((List)objAccountInfo).get(0);
            }
            //step 3 写入结算数据表
            pre_order.setRegid(accountRegInfo.get("regId")==null?null:accountRegInfo.get("regId").toString());
            pre_order.setRegreceipt(accountRegInfo.get("regReceipt")==null?null:accountRegInfo.get("regReceipt").toString());
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

    @Override
    public ResultBody queryRegisterInformation(RegInfoDto dto) throws GlobalErrorInfoException {
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
        RegInfoHisDto regInfoHisDto = new RegInfoHisDto(dto);
        regInfoHisDto.setCardNo(card.getCardno());
        regInfoHisDto.setCardType(card.getType());
        regInfoHisDto.setPatId(card.getPatid());
        ResultBody resultBody = serviceInvoke(ReflectMapUtil.beanToMap(regInfoHisDto));
        if(!IConst.HIS_SUCCESS.equals(resultBody.getCode())){
            //表示未查询到预约，挂号记录
            return new ResultBody(RegisteredErrorInfoEnum.REG_NODATA_ERROR);
        }
        //step 2 根据条件查询挂号预算记录
        OrderSettlementEntity orderSettlementEntity = new OrderSettlementEntity();
        orderSettlementEntity.setStatus("1");//表示预算
        if("0".equals(dto.getIsReg())){
            //表示预约
            orderSettlementEntity.setPreregflag("1");
        }else if("1".equals(dto.getIsReg())){
            //表示挂号
            orderSettlementEntity.setPreregflag("2");
        }
        orderSettlementEntity.setPatid(card.getPatid());
        orderSettlementEntity.setCardno(card.getCardno());
        orderSettlementEntity.setCardtype(card.getType());
        orderSettlementEntity.setIdcard(card.getIdcard_no());
        //开始日期
        Date startingDate = DateUtil.formatStringToDate(dto.getStartingDate(),DateUtil.FORMAT_DEFAULT);
        orderSettlementEntity.setTscdate(startingDate);
        List<OrderSettlementEntity> orderSettlementEntityList = orderSettlementMapper.queryByCond(orderSettlementEntity);
        //如果同一时间段内his查询出挂号记录 本地没有 则数据匹配异常
        if(orderSettlementEntityList.size() < 1){
            return new ResultBody(RegisteredErrorInfoEnum.REG_DATA_ERROR);
        }
        //step3 对比拼接数据
        Object regsobj = resultBody.getResult();
        Map regMap = null;
        List regList = null;
        if(regsobj instanceof Map){
            regMap = (Map) regsobj;
            for(Object obj:orderSettlementEntityList){
                OrderSettlementEntity orderSettlementEntityobj = (OrderSettlementEntity) obj;
                if(orderSettlementEntityobj.getReceiptno()!=null
                        &&regMap.get("regId")!=null
                        &&orderSettlementEntityobj.getReceiptno().equals(regMap.get("regId").toString())){
                    regMap.put("orderId",orderSettlementEntityobj.getOrderId());
                    regMap.put("personAmt",orderSettlementEntityobj.getPersonamt());//个人自付金额
                    regMap.put("discountsAmt",orderSettlementEntityobj.getDiscountsamt());//医院优惠金额
                    regMap.put("tradebalance",orderSettlementEntityobj.getRegamt());//订单总金额
                    regMap.put("pat_name",orderSettlementEntityobj.getPatName());//患者名
                    break;
                }
            }
            resultBody.setResult(regMap);
        }else{
            regList = (List)regsobj;
            for (Object obj: regList){
                Map regobj = (Map) obj;
                for(Object orderobj:orderSettlementEntityList){
                    OrderSettlementEntity orderSettlementEntityobj = (OrderSettlementEntity) orderobj;
                    if(orderSettlementEntityobj.getReceiptno()!=null
                            &&regobj.get("regId")!=null
                            &&orderSettlementEntityobj.getReceiptno().equals(regobj.get("regId").toString())){
                        regobj.put("orderId",orderSettlementEntityobj.getOrderId());
                        regobj.put("personAmt",orderSettlementEntityobj.getPersonamt());//个人自付金额
                        regobj.put("discountsAmt",orderSettlementEntityobj.getDiscountsamt());//医院优惠金额
                        regobj.put("tradebalance",orderSettlementEntityobj.getRegamt());//订单总金额
                        regobj.put("pat_name",orderSettlementEntityobj.getPatName());//患者名
                        break;
                    }
                }
            }
            resultBody.setResult(regList);
        }
        return resultBody;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public ResultBody cancelRegAccount(RegRefundDto dto) throws GlobalErrorInfoException {
        String key = setting.getRedisLockRegAccount()+dto.getOrderId();
        try {
            //此处存在线程安全问题 该笔订单同一时间只能执行一次his取消结算操作
            RedissLockUtil.lock(key, TimeUnit.MINUTES,5);
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
            //step2 取消结算
            CancelRegAccountDto cancelRegAccountDto = new CancelRegAccountDto();
            cancelRegAccountDto.setCardNo(card.getCardno());
            cancelRegAccountDto.setCardType(card.getType());
            cancelRegAccountDto.setPatId(card.getPatid());
            //查询结算信息
            OrderSettlementEntity orderSettlementEntity = new OrderSettlementEntity();
            orderSettlementEntity.setStatus("2");//查询结算信息
            orderSettlementEntity.setOrderId(dto.getOrderId());
            OrderSettlementEntity ordersettle = orderSettlementMapper.queryLimitOne(orderSettlementEntity);
            //拼接取消结算信息
            cancelRegAccountDto.setPayTradeno(ordersettle.getPaytradeno());
            cancelRegAccountDto.setPayWay(ordersettle.getPayway());
            cancelRegAccountDto.setRefundMoney(ordersettle.getPersonamt().toString());
            cancelRegAccountDto.setRefundTradeno(ordersettle.getOrderId());//退费流水号暂时用订单号
            cancelRegAccountDto.setRegId(ordersettle.getRegid());
            cancelRegAccountDto.setRegReceipt(ordersettle.getRegreceipt());
            cancelRegAccountDto.setRemark("正常退号");
            ResultBody resultBody = serviceInvoke(ReflectMapUtil.beanToMap(cancelRegAccountDto));
            if(!IConst.HIS_SUCCESS.equals(resultBody.getCode())){
                //表示his取消结算失败
                return new ResultBody(RegisteredErrorInfoEnum.REG_ORDER_CANCELERROR);
            }
            //step 4 记录取消结算
            //获取预算信息
            Object cancelInfo = resultBody.getResult();
            Map cancelRegInfo;
            if(cancelInfo instanceof Map){
                cancelRegInfo = (Map) cancelInfo;
            }else{
                cancelRegInfo = (Map) ((List)cancelInfo).get(0);
            }
            ordersettle.setRegreceipt(cancelRegInfo.get("refundReceipt")==null?null:cancelRegInfo.get("refundReceipt").toString());
            ordersettle.setStatus("3");//表示取消结算
            orderSettlementMapper.insertSelective(ordersettle);
            //step5 更改订单his状态
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderId(dto.getOrderId());
            orderEntity.setHisResult("04");//表示his结算取消
            orderMapper.updateOrderByOrderId(orderEntity);
            //step6 todo 异步退费
            Map refundMap = new HashMap();
            refundMap.put("orderId",dto.getOrderId());
            regAsyncService.saveRefund(refundMap);
            return new ResultBody();
        }catch (Exception e){
            logger.error(e.toString());
            throw new GlobalErrorInfoException(GlobalErrorInfoEnum.SYS_ERROR);
        }finally {
            RedissLockUtil.unlock(key);
        }

    }

    @Override
    public ResultBody queryClinicQueue(QueryClinicQueueDto dto) throws GlobalErrorInfoException {
        dto.setServiceId("queryClinicQueue");
        return serviceInvoke(ReflectMapUtil.beanToMap(dto));
    }

    @Override
    public ResultBody queryClinicPatientInfo(QueryClinicPatientInfoDto dto) throws GlobalErrorInfoException {
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
        QueryClinicPatientInfoHisDto queryClinicPatientInfoHisDto = new QueryClinicPatientInfoHisDto();
        queryClinicPatientInfoHisDto.setCardNo(card.getCardno());
        queryClinicPatientInfoHisDto.setCardType(card.getType());
        queryClinicPatientInfoHisDto.setPatId(card.getPatid());
        ResultBody resultBody = serviceInvoke(ReflectMapUtil.beanToMap(queryClinicPatientInfoHisDto));
        return resultBody;
    }

    private ResultBody serviceInvoke(Map param) throws GlobalErrorInfoException {
        return service.sendMsg(param, setting.getUrl());
    }

}
