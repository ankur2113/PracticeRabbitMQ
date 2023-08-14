package com.reset.PracticeRabbitMQ.api.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {
	
	
	public static final String ROUTING_KEY = "reset_key";
	public static final String QUEUE = "reset_queue";
	public static final String EXCHANGE = "reset_exchange";

	@Bean
	public Queue queue() {
		return new Queue("reset_queue");
	}
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange("reset_exchange");
	}
	
	@Bean
	public Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}
	
	@Bean
	public Jackson2JsonMessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate template(ConnectionFactory connection) {
		final RabbitTemplate rabbittemplate = new RabbitTemplate(connection);
		rabbittemplate.setMessageConverter(converter());
		return rabbittemplate;
	}
}
