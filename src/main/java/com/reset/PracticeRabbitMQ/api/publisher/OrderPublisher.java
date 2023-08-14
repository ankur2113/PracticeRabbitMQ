package com.reset.PracticeRabbitMQ.api.publisher;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reset.PracticeRabbitMQ.api.config.MessageConfig;
import com.reset.PracticeRabbitMQ.api.entity.Order;
import com.reset.PracticeRabbitMQ.api.entity.OrderStatus;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

	@Autowired
	private RabbitTemplate rabbittemplate;
	
	@PostMapping("/{restaurantname}")
	public String bookOrder(@RequestBody Order order, @PathVariable String restaurantname ) {
		order.setOrderid(UUID.randomUUID().toString());
		OrderStatus orderstatus = new OrderStatus(order, "PROCESSED", "Order placed from : "+restaurantname);
		rabbittemplate.convertAndSend(MessageConfig.EXCHANGE, MessageConfig.ROUTING_KEY, orderstatus);
		return "success!";
	}
}
