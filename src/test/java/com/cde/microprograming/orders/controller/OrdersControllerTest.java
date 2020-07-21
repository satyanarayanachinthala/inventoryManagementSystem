package com.cde.microprograming.orders.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cde.microprograming.orders.bo.OrdersBO;
import com.cde.microprograming.orders.model.Bills;
import com.cde.microprograming.orders.model.Orders;
import com.cde.microprograming.orders.model.OrdersProduct;
import com.cde.microprograming.orders.service.OrdersService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(controllers = OrdersController.class)
@WithMockUser
public class OrdersControllerTest {

	@Mock
	OrdersService ordersService;

	@InjectMocks
	OrdersController ordersController;

	@Autowired
	public MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(ordersController).build();
	}

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
	public void testGetOrder() throws Exception {
		when(ordersService.getOrder(1)).thenReturn(ordersBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order/1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}

	@Test
	public void testGetOrderException() throws Exception {
		when(ordersService.getOrder(2)).thenReturn(null);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order/2").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}

	@Test
	public void testGetOrders() throws Exception {
		List<OrdersBO> ordersBOList = new ArrayList<OrdersBO>();
		ordersBOList.add(ordersBO);
		when(ordersService.getOrders()).thenReturn(ordersBOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}

	@Test
	public void testCreateOrderException() throws Exception {
		ObjectMapper obj = new ObjectMapper();
		String data = obj.writeValueAsString(ordersBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/order").accept(MediaType.APPLICATION_JSON)
				.content(data).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}

	@Test
	public void testCreateOrder() throws Exception {
		ordersBO.setId(0);
		ObjectMapper obj = new ObjectMapper();
		String data = obj.writeValueAsString(ordersBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/order").accept(MediaType.APPLICATION_JSON)
				.content(data).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}

	@Test
	public void testupdateOrder() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/order/1").accept(MediaType.APPLICATION_JSON)
				.content(ordersBO.toString()).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}

	@Test
	public void testDeleteOrder() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/order/1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}

}
