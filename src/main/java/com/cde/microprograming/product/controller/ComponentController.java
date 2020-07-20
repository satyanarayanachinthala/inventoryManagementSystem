package com.cde.microprograming.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cde.microprograming.orders.bo.IdResponseBO;
import com.cde.microprograming.product.bo.ComponentBO;
import com.cde.microprograming.product.service.ComponentService;

@RestController
public class ComponentController {

	@Autowired
	ComponentService componentService;

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PostMapping("/component")
	public ResponseEntity<IdResponseBO> createComponent(@RequestBody ComponentBO componentBO) {
		if (componentBO.getId() == 0) {
			int id = componentService.createComponent(componentBO);
			return ResponseEntity.accepted().body(new IdResponseBO(id));
		} 
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "data already present");
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PutMapping("/component/{id}")
	public ResponseEntity<String> updateComponent(@PathVariable int id, @RequestBody ComponentBO componentBO) {
		componentBO.setId(id);
		componentService.updateComponent(componentBO);
		return ResponseEntity.accepted().body("Successfully Updated");
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/component/{id}")
	public ComponentBO getComponent(@PathVariable int id) {
		ComponentBO component = componentService.getComponent(id);
		if (component == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "component not found");
		return component;
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/component")
	public List<ComponentBO> getAllComponents() {
		return componentService.getAllComponents();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/component/{id}")
	public ResponseEntity<String> deleteComponent(@PathVariable int id) {
		componentService.deleteComponent(id);
		return ResponseEntity.accepted().body("Successfully Deleted");
	}

}
