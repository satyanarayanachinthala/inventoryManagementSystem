package com.cde.microprograming.orders.service;

import java.util.List;

import com.cde.microprograming.orders.bo.OrdersBO;
import com.cde.microprograming.orders.model.Orders;

public interface OrdersService {

	Orders createOrder(OrdersBO ordersBO);

	Orders updateOrder(OrdersBO ordersBO);

	OrdersBO getOrder(int id);

	List<OrdersBO> getOrders();

	void deleteOrder(int id);

}
