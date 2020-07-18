package com.cde.microprograming.orders.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cde.microprograming.orders.bo.OrdersBO;
import com.cde.microprograming.orders.service.OrdersService;

@RestController
@CrossOrigin
public class OrdersController {

	Logger logger = LoggerFactory.getLogger(OrdersController.class);

	@Autowired
	OrdersService ordersService;

	@PostMapping("/order")
	public ResponseEntity<String> createOrder(@RequestBody OrdersBO ordersBO) {
		if (ordersBO.getId() == 0) {
			ordersService.createOrder(ordersBO);
			return ResponseEntity.accepted().body("Successfully Created");
		} else {
			return ResponseEntity.unprocessableEntity().body("data already present");
		}

	}

	@PutMapping("/order/{id}")
	public ResponseEntity<String> updateOrder(@PathVariable int id, OrdersBO ordersBO) {
		ordersBO.setId(id);
		ordersService.updateOrder(ordersBO);
		return ResponseEntity.accepted().body("Successfully Updated");
	}

	@GetMapping("/order/{id}")
	public OrdersBO getOrder(@PathVariable int id) {
		return ordersService.getOrder(id);
	}

	@GetMapping("/order")
	public List<OrdersBO> getOrders() {
		return ordersService.getOrders();
	}

	@DeleteMapping("/order/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable(value = "id") int id) {
		ordersService.deleteOrder(id);
		return ResponseEntity.accepted().body("Successfully Deleted");
	}

}
