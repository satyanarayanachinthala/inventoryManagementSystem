package com.cde.microprograming.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cde.microprograming.product.bo.RawMaterialBO;
import com.cde.microprograming.product.service.RawMaterialService;

@RestController
public class RawMaterialController {

	@Autowired
	RawMaterialService rawMaterialService;

	@PostMapping("/rawMaterial")
	public ResponseEntity<String> createRawMaterial(@RequestBody RawMaterialBO rawMaterialBO) {
		if (rawMaterialBO.getId() == 0) {
			rawMaterialService.createRawMaterial(rawMaterialBO);
			return ResponseEntity.accepted().body("Successfully Created");
		} else {
			return ResponseEntity.unprocessableEntity().body("data already present");
		}
	}

	@PutMapping("/rawMaterial/{id}")
	public ResponseEntity<String> updateRawMaterial(@PathVariable int id, @RequestBody RawMaterialBO rawMaterialBO) {
		rawMaterialBO.setId(id);
		rawMaterialService.updateRawMaterial(rawMaterialBO);
		return ResponseEntity.accepted().body("Successfully Updated");
	}

	
	@GetMapping("/rawMaterial/{id}")
	@Cacheable(value = "rawMaterial", key = "#id")
	public RawMaterialBO getRawMaterial(@PathVariable int id) {
		return rawMaterialService.getRawMaterial(id);
	}

	@GetMapping("/rawMaterial")
	public List<RawMaterialBO> getAllRawMaterials() {
		return rawMaterialService.getAllRawMaterials();
	}

	@DeleteMapping("/rawMaterial/{id}")
	public ResponseEntity<String> deleteRawMaterial(@PathVariable int id) {
		rawMaterialService.deleteRawMaterial(id);
		return ResponseEntity.accepted().body("Successfully Deleted");
	}

}
