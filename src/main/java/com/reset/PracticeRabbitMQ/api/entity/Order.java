package com.reset.PracticeRabbitMQ.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {

	private String orderid;
	private String name;
	private int qty;
	private double price;
}
