package com.cde.microprograming.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cde.microprograming.orders.bo.IdResponseBO;
import com.cde.microprograming.product.bo.ProductBO;
import com.cde.microprograming.product.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/product")
	public ResponseEntity<IdResponseBO> createProduct(@RequestBody ProductBO productBO) {
		if (productBO.getId() == 0) {
			int id = productService.createProduct(productBO);
			return ResponseEntity.accepted().body(new IdResponseBO(id));
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "data already present");
	}

	@PutMapping("/product/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody ProductBO productBO) {
		productBO.setId(id);
		productService.updateProduct(productBO);
		return ResponseEntity.accepted().body("Successfully Updated");
	}

	@GetMapping("/product/{id}")
	public ProductBO getProduct(@PathVariable int id) {
		ProductBO product = productService.getProduct(id);
		if (product == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "rawMaterial not found");
		return product;
	}

	@GetMapping("/product")
	public List<ProductBO> getAllProducts() {
		return productService.getAllProducts();
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id) {
		productService.deleteProduct(id);
		return ResponseEntity.accepted().body("Successfully Deleted");
	}

}
