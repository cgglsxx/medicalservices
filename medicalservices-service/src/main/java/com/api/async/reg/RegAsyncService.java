package com.api.async.reg;

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

import java.util.Map;
import java.util.concurrent.Future;

@Service
public class RegAsyncService {

    private static final Logger logger = LoggerFactory.getLogger(RegAsyncService.class);
    @Autowired
    OrderMapper orderMapper;
    @Async("regAsyncExecutor")
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
    @Async("regAsyncExecutor")
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