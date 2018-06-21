package com.api.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RabbitConfig {


	@Value("${rabbitmq.host}")
	private String host;

	@Value("${rabbitmq.port}")
	private int port;

	@Value("${rabbitmq.username}")
	private String username;

	@Value("${rabbitmq.password}")
	private String password;

	@Value("${rabbitmq.publisher-confirms}")
	private boolean publisherConfirm;

	@Value("${rabbitmq.virtual-host}")
	private String virtualHost;



	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(host);
		connectionFactory.setPort(port);
		connectionFactory.setUsername(username);
		connectionFactory.setPassword(password);
		connectionFactory.setVirtualHost(virtualHost);
		connectionFactory.setPublisherConfirms(publisherConfirm); //必须要设置
		return connectionFactory;
	}

	//////////////////////////////start 挂号支付 通知his（失败进行业务补偿）////////////////////////////////
	/**
	 * 发送到该队列的message会在一段时间后过期进入到延迟队列
	 * 每个message可以控制自己的失效时间
	 */
	public final static String DELAY_REGPAY_QUEUE_MSG = "delay_regpay_queue";

	/**
	 * DLX
	 */
	public final static String DELAY_REGPAY_EXCHANGE_NAME = "delay_regpay_exchange";


	/**
	 * 正常消费的队列
	 */
	public final static String PROCESS_REGPAY_QUEUE = "process_regpay_queue";

	/**
	 * 正常队列对应的exchange
	 */
	public final static String PROCESS_REGPAY_EXCHANGE_NAME = "process_regpay_exchange";


	public static String REGPAY_ROUTING_KEY = "regpay.delay";

	/**
	 * 延迟队列 exchange
	 * @return
	 */
	@Bean
	public DirectExchange delayRegpayExchange() {
		return new DirectExchange(DELAY_REGPAY_EXCHANGE_NAME);
	}

	/**
	 * 正常队列 exchange
	 * @return
	 */
	@Bean
	public DirectExchange processRegpayExchange() {
		return new DirectExchange(PROCESS_REGPAY_EXCHANGE_NAME);
	}

	@Bean
	Queue delayRegpayQueuePerMessageTTL(){
		return QueueBuilder.durable(DELAY_REGPAY_QUEUE_MSG)
				.withArgument("x-dead-letter-exchange", PROCESS_REGPAY_EXCHANGE_NAME) // DLX，dead letter发送到的exchange
				.withArgument("x-dead-letter-routing-key", REGPAY_ROUTING_KEY) // dead letter携带的routing key
				.build();
	}

	@Bean
	public Queue processRegpayQueue() {
		return QueueBuilder.durable(PROCESS_REGPAY_QUEUE)
				.build();
	}



	/**
	 * 将延迟队列与exchange绑定，即到达指定时间之后需要转交给queue消费
	 * @return
	 */
	@Bean
	Binding dlxRegpayBinding() {
		return BindingBuilder.bind(delayRegpayQueuePerMessageTTL())
				.to(delayRegpayExchange())
				.with(REGPAY_ROUTING_KEY);
	}
	/**
	 * 将正常队列与exchange绑定
	 * @return
	 */
	@Bean
	Binding queueRegpayBinding() {
		return BindingBuilder.bind(processRegpayQueue())
				.to(processRegpayExchange())
				.with(REGPAY_ROUTING_KEY);
	}

	//////////////////////////////end 挂号支付 通知his（失败进行业务补偿）////////////////////////////////

//////////////////////////////start 挂号锁号取消 通知his（失败进行业务补偿）////////////////////////////////
	/**
	 * 发送到该队列的取消锁号message会在一段时间后过期进入到取消锁号的延时队列(从而达到定时的作用)
	 * 每个message可以控制自己的失效时间
	 */
	public final static String DELAY_CANCELLOCKREG_QUEUE_MSG = "delay_cancellockreg_queue";

	/**
	 * DLX
	 */
	public final static String DELAY_CANCELLOCKREG_EXCHANGE_NAME = "delay_cancellockreg_exchange";


	/**
	 * 取消锁号正常消费的队列
	 */
	public final static String PROCESS_CANCELLOCKREG_QUEUE = "process_cancellockreg_queue";

	/**
	 * 正常队列对应的exchange
	 */
	public final static String PROCESS_CANCELLOCKREG_EXCHANGE_NAME = "process_cancellockreg_exchange";


	public static String CANCELLOCKREG_ROUTING_KEY = "cancellockreg.delay";

	/**
	 * 延迟队列 exchange
	 * @return
	 */
	@Bean
	public DirectExchange delayCancellockregExchange() {
		return new DirectExchange(DELAY_CANCELLOCKREG_EXCHANGE_NAME);
	}

	/**
	 * 正常队列 exchange
	 * @return
	 */
	@Bean
	public DirectExchange processCancellockregExchange() {
		return new DirectExchange(PROCESS_CANCELLOCKREG_EXCHANGE_NAME);
	}

	@Bean
	Queue delayCancellockregQueuePerMessageTTL(){
		return QueueBuilder.durable(DELAY_CANCELLOCKREG_QUEUE_MSG)
				.withArgument("x-dead-letter-exchange", PROCESS_CANCELLOCKREG_EXCHANGE_NAME) // DLX，dead letter发送到的exchange
				.withArgument("x-dead-letter-routing-key", CANCELLOCKREG_ROUTING_KEY) // dead letter携带的routing key
				.build();
	}

	@Bean
	public Queue processCancellockregQueue() {
		return QueueBuilder.durable(PROCESS_CANCELLOCKREG_QUEUE)
				.build();
	}



	/**
	 * 将延迟队列与exchange绑定，即到达指定时间之后需要转交给queue消费
	 * @return
	 */
	@Bean
	Binding dlxBindingCancellockreg() {
		return BindingBuilder.bind(delayCancellockregQueuePerMessageTTL())
				.to(delayCancellockregExchange())
				.with(CANCELLOCKREG_ROUTING_KEY);
	}
	/**
	 * 将正常队列与exchange绑定
	 * @return
	 */
	@Bean
	Binding queueBindingCancellockreg() {
		return BindingBuilder.bind(processCancellockregQueue())
				.to(processCancellockregExchange())
				.with(CANCELLOCKREG_ROUTING_KEY);
	}

	//////////////////////////////end 挂号锁号取消 通知his（失败进行业务补偿）////////////////////////////////

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	//必须是prototype类型
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		return template;
	}

}