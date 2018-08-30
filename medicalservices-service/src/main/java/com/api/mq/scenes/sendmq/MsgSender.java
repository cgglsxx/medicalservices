package com.api.mq.scenes.sendmq;

import com.api.mq.config.RabbitConfig;
import com.api.mq.model.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
@Component
public class MsgSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public void sendToMqForNoticeHis(Msg msg) {
        System.out.println("发送异步通知his系统缴费成功信息至消息队列" + msg.toString());
        this.rabbitTemplate.convertAndSend(msg.getQueue(), msg);
    }

    /**
     * 设定时间后取消锁号
     * @param msg
     */
    public void sendToMqForDelayCancelReg(Msg msg) {
        //延迟发送消息的时间 毫秒单位
        final String time = msg.getTime();
        rabbitTemplate.convertAndSend(RabbitConfig.DELAY_CANCELLOCKREG_EXCHANGE_NAME,RabbitConfig.CANCELLOCKREG_ROUTING_KEY, msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(time);
                return message;
            }
        });
    }
    /**
     * 设定时间后通知挂号缴费保存
     * @param msg
     */
    public void sendToMqForDelayRegPay(Msg msg) {
        //延迟发送消息的时间 毫秒单位
        final String time = msg.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("缴费补偿延时"+time+"消息发送时间:" + sdf.format(new Date()));
        rabbitTemplate.convertAndSend(RabbitConfig.DELAY_REGPAY_EXCHANGE_NAME,RabbitConfig.REGPAY_ROUTING_KEY, msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(time);
                return message;
            }
        });
    }
}
