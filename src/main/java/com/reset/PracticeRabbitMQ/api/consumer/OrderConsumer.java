package com.reset.PracticeRabbitMQ.api.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.reset.PracticeRabbitMQ.api.config.MessageConfig;
import com.reset.PracticeRabbitMQ.api.entity.OrderStatus;

@Component
public class OrderConsumer {
	
	@RabbitListener(queues = MessageConfig.QUEUE)
	public void consumeMessagefromqueue(OrderStatus orderstatus) {
		System.out.println("Message recieved: "+ orderstatus);
	}
}
