package com.cde.microprograming.orders.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cde.microprograming.exception.InventoryNotFoundException;
import com.cde.microprograming.orders.bo.OrdersBO;
import com.cde.microprograming.orders.dao.OrdersDAO;
import com.cde.microprograming.orders.model.Orders;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	OrdersDAO ordersDAO;

	@Override
	public Orders createOrder(OrdersBO ordersBO) {
		Orders orders = new Orders(ordersBO);
		return ordersDAO.save(orders);
	}

	@Override
	public Orders updateOrder(OrdersBO ordersBO) {
		Optional<Orders> ordersData = ordersDAO.findById(ordersBO.getId());
		if (!ordersData.isPresent()) {
			throw new InventoryNotFoundException("data not found " + ordersBO.getId());
		}
		Orders orders = new Orders(ordersBO);
		return ordersDAO.save(orders);
	}

	@Override
	public OrdersBO getOrder(int id) {
		Optional<Orders> orders = ordersDAO.findById(id);
		if (!orders.isPresent()) {
			throw new InventoryNotFoundException("data not found " + id);
		}
		return new OrdersBO(orders.get());
	}

	@Override
	public List<OrdersBO> getOrders() {
		List<Orders> orders = ordersDAO.findAll();
		return orders.stream().map(ordersData -> new OrdersBO(ordersData)).collect(Collectors.toList());
	}

	@Override
	public void deleteOrder(int id) {
		ordersDAO.deleteById(id);
	}

}
