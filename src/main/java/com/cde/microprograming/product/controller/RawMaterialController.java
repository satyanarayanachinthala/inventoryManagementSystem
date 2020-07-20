package com.cde.microprograming.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
import com.cde.microprograming.product.bo.RawMaterialBO;
import com.cde.microprograming.product.service.RawMaterialService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "rawmaterial", description = "raw material information")
public class RawMaterialController {

	@Autowired
	RawMaterialService rawMaterialService;

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PostMapping("/rawMaterial")
	public ResponseEntity<IdResponseBO> createRawMaterial(@RequestBody RawMaterialBO rawMaterialBO) {
		if (rawMaterialBO.getId() == 0) {
			int id = rawMaterialService.createRawMaterial(rawMaterialBO);
			return ResponseEntity.accepted().body(new IdResponseBO(id));
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "data already present");
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PutMapping("/rawMaterial/{id}")
	public ResponseEntity<String> updateRawMaterial(@PathVariable int id, @RequestBody RawMaterialBO rawMaterialBO) {
		rawMaterialBO.setId(id);
		rawMaterialService.updateRawMaterial(rawMaterialBO);
		return ResponseEntity.accepted().body("Successfully Updated");
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/rawMaterial/{id}")
	@Cacheable(value = "rawMaterial", key = "#id")
	public RawMaterialBO getRawMaterial(@PathVariable int id) {
		RawMaterialBO rawMaterial = rawMaterialService.getRawMaterial(id);
		if (rawMaterial == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "rawMaterial not found");
		return rawMaterial;
	}

	@ApiOperation(value = "view a list of raw materials", response = Iterable.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
	
	@GetMapping("/rawMaterial")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public List<RawMaterialBO> getAllRawMaterials() {
		return rawMaterialService.getAllRawMaterials();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/rawMaterial/{id}")
	public ResponseEntity<String> deleteRawMaterial(@PathVariable int id) {
		rawMaterialService.deleteRawMaterial(id);
		return ResponseEntity.accepted().body("Successfully Deleted");
	}

}
