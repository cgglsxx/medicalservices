package com.api.mq.scenes.receivemq;

import com.api.adapter.impl.HISCommonInterfaceTransAdapterImpl;
import com.api.constant.IConst;
import com.api.dto.register.CancelRegisterDto;
import com.api.mq.config.RabbitConfig;
import com.api.mq.model.Msg;
import com.api.mq.scenes.sendmq.MsgSender;
import com.api.registered.dao.OrderMapper;
import com.api.registered.dao.RegistrationDetailMapper;
import com.api.registered.domain.OrderEntity;
import com.api.registered.domain.RegistrationDetailEntity;
import com.api.result.GlobalErrorInfoException;
import com.api.result.ResultBody;
import com.api.result.messageenum.RegisteredErrorInfoEnum;
import com.api.setting.HisSetting;
import com.api.util.RedissLockUtil;
import com.api.util.ReflectMapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 处理异步通知his挂号缴费成功消息
 */
@Component
public class MsgReceiver {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    HisSetting setting;
    @Autowired
    MsgSender msgSender;
    @Autowired
    RegistrationDetailMapper registrationDetailMapper;
    @Autowired
    HISCommonInterfaceTransAdapterImpl service;
    @Autowired
    OrderMapper orderMapper;
    @RabbitListener(queues = {RabbitConfig.PROCESS_CANCELLOCKREG_QUEUE})
    @RabbitHandler
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void processForCancelReg(Msg msg){
        //step1获取登记信息 判断订单是否支付
        RegistrationDetailEntity registrationDetailEntity = (RegistrationDetailEntity) msg.getObj();
        logger.info("订单（"+registrationDetailEntity.getOrderId()+"）接收到取消锁号消息通知:当前进行第"+msg.getCount()+"次取消锁号");
        String key = setting.getRedisLockRegAccount()+registrationDetailEntity.getOrderId();
        try {
            //此处存在线程安全问题 该笔订单同一时间只能执行一次操作
            RedissLockUtil.lock(key, TimeUnit.MINUTES, 5);
            boolean flag = true;//表示取消锁号成功
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderId(registrationDetailEntity.getOrderId());
            OrderEntity order = orderMapper.queryLimitOne(orderEntity);
            if(!(order == null || (order != null && "01".equals(order.getPayResult())))){
                //表示该订单不满足自动取消预约 无需取消
                return;
            }
            //step 2 取消锁号
            CancelRegisterDto cancelRegisterDto = new CancelRegisterDto();
            cancelRegisterDto.setCardNo(registrationDetailEntity.getCardno());
            cancelRegisterDto.setCardType(registrationDetailEntity.getCardtype());
            cancelRegisterDto.setPatId(registrationDetailEntity.getPatid());
            cancelRegisterDto.setYydjh(registrationDetailEntity.getYydjh());
            ResultBody lockBody = serviceInvoke(ReflectMapUtil.beanToMap(cancelRegisterDto));
            if(!IConst.HIS_SUCCESS.equals(lockBody.getCode())){
                //表示取消锁号失败
                flag = false;
                logger.error("订单（"+registrationDetailEntity.getOrderId()+"）进行第"+msg.getCount()+"次取消锁号失败");
            }else{
                RegistrationDetailEntity entity = new RegistrationDetailEntity();
                entity.setStatus("2");
                entity.setOrderId(registrationDetailEntity.getOrderId());
                registrationDetailMapper.updateRegistrationDetailByOrderId(entity);
            }
            //step3 根据情况配置是否进行补偿业务
            if(!flag && msg.getCount() <= 3){
                logger.info("订单（"+registrationDetailEntity.getOrderId()+"）发送异步通知his系统取消锁号信息 ，当前第"+msg.getCount()+"次发送取消锁号信息");
                //发送取消锁号延迟mq信息
                String time = msg.getCount()*10000+"";
                msg.setTime(time);
                msg.setCount(msg.getCount()+1);
                msg.setObj(registrationDetailEntity);
                msgSender.sendToMqForDelayCancelReg(msg);
            }
        }catch (Exception e){

        }finally {

        }

    }
    @RabbitListener(queues = {RabbitConfig.PROCESS_REGPAY_QUEUE})
    @RabbitHandler
    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void processForNoticeHisRegPay(Msg msg)  {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间: " + sdf.format(new Date()) + " ---> msg：【" + msg + "]");
        System.out.println("获取到异步通知his系统挂号缴费信息 开始通知his，当前第"+msg.getCount()+"次通知");
        //模拟调用his第三次才成功
        boolean flag = false;
        if(msg.getCount() == 3){
            flag = true;
        }
        if(!flag){
            System.out.println("发送异步通知his系统挂号缴费信息 ，当前第"+msg.getCount()+"次发送延迟mq信息");
            //发送延迟mq信息
            String time = msg.getCount()*10000+"";
            msg.setTime(time);
            msg.setCount(msg.getCount()+1);
            msgSender.sendToMqForDelayRegPay(msg);
        }
    }
    private ResultBody serviceInvoke(Map param) throws GlobalErrorInfoException {
        return service.sendMsg(param, setting.getUrl());
    }

}
