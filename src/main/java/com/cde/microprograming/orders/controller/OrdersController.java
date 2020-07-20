package com.cde.microprograming.orders.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cde.microprograming.orders.bo.IdResponseBO;
import com.cde.microprograming.orders.bo.OrdersBO;
import com.cde.microprograming.orders.service.OrdersService;

@RestController
@CrossOrigin
public class OrdersController {

	Logger logger = LoggerFactory.getLogger(OrdersController.class);

	@Autowired
	OrdersService ordersService;

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PostMapping("/order")
	public ResponseEntity<IdResponseBO> createOrder(@RequestBody OrdersBO ordersBO) {
		if (ordersBO.getId() == 0) {
			int id = ordersService.createOrder(ordersBO);
			return ResponseEntity.accepted().body(new IdResponseBO(id));
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "data already present");
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PutMapping("/order/{id}")
	public ResponseEntity<String> updateOrder(@PathVariable int id, OrdersBO ordersBO) {
		ordersBO.setId(id);
		ordersService.updateOrder(ordersBO);
		return ResponseEntity.accepted().body("Successfully Updated");
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/order/{id}")
	public OrdersBO getOrder(@PathVariable int id) {
		OrdersBO order = ordersService.getOrder(id);
		if (order == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "component not found");
		return order;
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/order")
	public List<OrdersBO> getOrders() {
		return ordersService.getOrders();
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@DeleteMapping("/order/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable(value = "id") int id) {
		ordersService.deleteOrder(id);
		return ResponseEntity.accepted().body("Successfully Deleted");
	}

}
