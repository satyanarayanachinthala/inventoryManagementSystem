package com.cde.microprograming.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cde.microprograming.product.model.Product;
import com.cde.microprograming.product.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/product")
	public ResponseEntity<String> createProduct(@RequestBody Product product) {
		if (product.getId() == 0) {
			productService.createProduct(product);
			return ResponseEntity.accepted().body("Successfully Created");
		} else {
			return ResponseEntity.unprocessableEntity().body("data already present");
		}
	}

}
