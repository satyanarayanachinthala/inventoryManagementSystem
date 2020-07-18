package com.cde.microprograming.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cde.microprograming.product.bo.ComponentBO;
import com.cde.microprograming.product.service.ComponentService;

@RestController
public class ComponentController {

	@Autowired
	ComponentService componentService;

	@PostMapping("/component")
	public ResponseEntity<String> createComponent(@RequestBody ComponentBO componentBO) {
		if (componentBO.getId() == 0) {
			componentService.createComponent(componentBO);
			return ResponseEntity.accepted().body("Successfully Created");
		} else {
			return ResponseEntity.unprocessableEntity().body("data already present");
		}
	}

	@PutMapping("/component/{id}")
	public ResponseEntity<String> updateComponent(@PathVariable int id, @RequestBody ComponentBO componentBO) {
		componentBO.setId(id);
		componentService.updateComponent(componentBO);
		return ResponseEntity.accepted().body("Successfully Updated");
	}

	@GetMapping("/component/{id}")
	public ComponentBO getComponent(@PathVariable int id) {
		return componentService.getComponent(id);
	}

	@GetMapping("/component")
	public List<ComponentBO> getAllComponents() {
		return componentService.getAllComponents();
	}

	@DeleteMapping("/component/{id}")
	public ResponseEntity<String> deleteComponent(@PathVariable int id) {
		componentService.deleteComponent(id);
		return ResponseEntity.accepted().body("Successfully Deleted");
	}

}
