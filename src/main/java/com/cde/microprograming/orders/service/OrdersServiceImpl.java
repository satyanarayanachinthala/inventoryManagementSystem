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
	
	private static final String DATA_NOT_FOUND = "data not found";

	@Autowired
	OrdersDAO ordersDAO;

	@Override
	public int createOrder(OrdersBO ordersBO) {
		Orders orders = new Orders(ordersBO);
		return ordersDAO.save(orders).getId();
	}

	@Override
	public void updateOrder(OrdersBO ordersBO) {
		Optional<Orders> ordersData = ordersDAO.findById(ordersBO.getId());
		if (!ordersData.isPresent())
			throw new InventoryNotFoundException(DATA_NOT_FOUND + ordersBO.getId());

		Orders orders = new Orders(ordersBO);
		ordersDAO.save(orders);
	}

	@Override
	public OrdersBO getOrder(int id) {
		Optional<Orders> orders = ordersDAO.findById(id);
		if (!orders.isPresent()) {
			throw new InventoryNotFoundException(DATA_NOT_FOUND + id);
		}
		return new OrdersBO(orders.get());
	}

	@Override
	public List<OrdersBO> getOrders() {
		List<Orders> orders = ordersDAO.findAll();
		return orders.stream().map(OrdersBO::new).collect(Collectors.toList());
	}

	@Override
	public void deleteOrder(int id) {
		Optional<Orders> orders = ordersDAO.findById(id);
		if (!orders.isPresent())
			throw new InventoryNotFoundException(DATA_NOT_FOUND + id);
		ordersDAO.deleteById(id);
	}

}
