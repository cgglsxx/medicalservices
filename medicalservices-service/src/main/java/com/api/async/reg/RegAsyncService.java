package com.api.async.reg;

import com.api.dto.clinic.ClinicAccountDto;
import com.api.dto.inpatient.InpatientPrePaymentDto;
import com.api.dto.register.RegAccountDto;
import com.api.registered.dao.OrderMapper;
import com.api.registered.domain.OrderEntity;
import com.api.result.ResultBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.Future;

@Service
public class RegAsyncService {

    private static final Logger logger = LoggerFactory.getLogger(RegAsyncService.class);
    @Autowired
    OrderMapper orderMapper;
    @Async("regAsyncExecutor")
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public Future<ResultBody> updateRegOrderPay(RegAccountDto dto) {
        ResultBody result = new ResultBody();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(dto.getOrderId());
        orderEntity.setPayResult("02");
        orderEntity.setPayway(dto.getPayway());
        orderEntity.setPayType(dto.getPayType());
        orderEntity.setPaytradeno(dto.getPayTradeno());
        orderMapper.updateOrderByOrderId(orderEntity);
        return new AsyncResult<>(result);
    }
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public Future<ResultBody> updateClinicOrderPay(ClinicAccountDto dto) {
        ResultBody result = new ResultBody();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(dto.getOrderId());
        orderEntity.setPayResult("02");
        orderEntity.setPayway(dto.getPayway());
        orderEntity.setPayType(dto.getPayType());
        orderEntity.setPaytradeno(dto.getPayTradeno());
        orderMapper.updateOrderByOrderId(orderEntity);
        return new AsyncResult<>(result);
    }
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public Future<ResultBody> updateInpatientOrderPay(InpatientPrePaymentDto dto) {
        ResultBody result = new ResultBody();
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(dto.getOrderId());
        orderEntity.setPayResult("02");
        orderEntity.setPayway(dto.getPayway());
        orderEntity.setPayType(dto.getPayType());
        orderEntity.setPaytradeno(dto.getPayTradeno());
        orderMapper.updateOrderByOrderId(orderEntity);
        return new AsyncResult<>(result);
    }
    @Async("regAsyncExecutor")
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public Future<ResultBody> saveRefund(Map param) {
        ResultBody result = new ResultBody();
        //订单id
        String orderId = param.get("orderId").toString();
        logger.error("订单"+orderId+"开始进行自动退费");
        //模拟退费成功 更新订单支付状态
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(orderId);
        orderEntity.setPayResult("04");
        orderMapper.updateOrderByOrderId(orderEntity);
        return new AsyncResult<>(result);
    }


}