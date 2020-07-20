package com.cde.microprograming.orders.service;

import java.util.List;

import com.cde.microprograming.orders.bo.OrdersBO;

public interface OrdersService {

	int createOrder(OrdersBO ordersBO);

	void updateOrder(OrdersBO ordersBO);

	OrdersBO getOrder(int id);

	List<OrdersBO> getOrders();

	void deleteOrder(int id);

}
