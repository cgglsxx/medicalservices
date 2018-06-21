package com.api.mq.scenes.receivemq;

import com.api.mq.config.RabbitConfig;
import com.api.mq.model.Msg;
import com.api.mq.scenes.sendmq.MsgSender;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 处理异步通知his挂号缴费成功消息
 */
@Component

public class MsgReceiver {
    @Autowired
    MsgSender msgSender;

    @RabbitListener(queues = {RabbitConfig.PROCESS_CANCELLOCKREG_QUEUE})
    @RabbitHandler
    public void processForCancelReg(Msg msg) throws InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间: " + sdf.format(new Date()) + " ---> msg：【" + msg + "]");
        System.out.println("获取到异步通知his系统取消锁号信息 开始通知his，当前第"+msg.getCount()+"次通知");
        //模拟调用his第三次才成功
        boolean flag = false;
        if(msg.getCount() == 3){
            flag = true;
        }
        if(!flag){
            System.out.println("发送异步通知his系统取消锁号信息 ，当前第"+msg.getCount()+"次发送延迟mq信息");
            //发送延迟mq信息
            String time = msg.getCount()*10000+"";
            msg.setTime(time);
            msg.setCount(msg.getCount()+1);
            msgSender.sendToMqForDelayCancelReg(msg);
        }
    }
    @RabbitListener(queues = {RabbitConfig.PROCESS_REGPAY_QUEUE})
    @RabbitHandler
    public void processForNoticeHisRegPay(Msg msg) throws InterruptedException {
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

}
