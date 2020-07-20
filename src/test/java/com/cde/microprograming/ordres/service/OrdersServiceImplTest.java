package com.cde.microprograming.ordres.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.cde.microprograming.exception.InventoryNotFoundException;
import com.cde.microprograming.orders.bo.OrdersBO;
import com.cde.microprograming.orders.dao.OrdersDAO;
import com.cde.microprograming.orders.model.Bills;
import com.cde.microprograming.orders.model.Orders;
import com.cde.microprograming.orders.model.OrdersProduct;
import com.cde.microprograming.orders.service.OrdersServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class OrdersServiceImplTest {

	@Mock
	OrdersDAO ordersDAO;

	@InjectMocks
	OrdersServiceImpl ordersServiceImpl;

	Orders orders;

	OrdersBO ordersBO;

	@Before
	public void init() {
		orders = new Orders();
		orders.setId(1);
		orders.setProductId(1);
		orders.setAddressId(1);
		orders.setProductId(1);
		orders.setStatus("in progress");
		OrdersProduct ordersProduct = new OrdersProduct();
		ordersProduct.setId(1);
		ordersProduct.setProductId(1);
		ordersProduct.setQuantity(1);
		List<OrdersProduct> ordersProducts = new ArrayList<OrdersProduct>();
		ordersProducts.add(ordersProduct);
		orders.setOrdersProducts(ordersProducts);
		Bills bills = new Bills();
		bills.setId(1);
		bills.setPaymentMethod("credit card");
		orders.setBills(bills);

		ordersBO = new OrdersBO(orders);
	}

	@Test
	public void testGetOrder() {
		when(ordersDAO.findById(1)).thenReturn(Optional.of(orders));
		assertEquals("in progress", ordersServiceImpl.getOrder(1).getStatus());
	}

	@Test(expected = InventoryNotFoundException.class)
	public void testGetOrderException() {
		when(ordersDAO.findById(2)).thenReturn(Optional.empty());
		ordersServiceImpl.getOrder(2);
	}

	@Test
	public void testGetOrders() {
		List<Orders> ordersListData = new ArrayList<Orders>();
		ordersListData.add(orders);
		when(ordersDAO.findAll()).thenReturn(ordersListData);
		assertEquals("in progress", ordersServiceImpl.getOrders().get(0).getStatus());
	}

	@Test(expected = InventoryNotFoundException.class)
	public void testDeleteOrderException() {
		when(ordersDAO.findById(2)).thenReturn(Optional.empty());
		ordersServiceImpl.deleteOrder(2);
	}

	@Test
	public void testDeleteOrder() {
		when(ordersDAO.findById(1)).thenReturn(Optional.of(orders));
		ordersServiceImpl.deleteOrder(1);
		assertEquals("in progress", orders.getStatus());
	}

	@Test(expected = InventoryNotFoundException.class)
	public void testUpdateOrderOrderException() {
		ordersBO.setId(2);
		when(ordersDAO.findById(2)).thenReturn(Optional.empty());
		ordersServiceImpl.updateOrder(ordersBO);
	}

	@Test
	public void testUpdateOrderOrder() {
		when(ordersDAO.findById(1)).thenReturn(Optional.of(orders));
		ordersServiceImpl.updateOrder(ordersBO);
		assertEquals("in progress", orders.getStatus());
	}

	@Test
	public void testCreateOrder() {
		when(ordersDAO.save(ArgumentMatchers.any(Orders.class))).thenReturn(orders);
		assertEquals(1, ordersServiceImpl.createOrder(ordersBO));
	}

}
