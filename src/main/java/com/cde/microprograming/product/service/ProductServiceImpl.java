package com.cde.microprograming.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cde.microprograming.exception.InventoryNotFoundException;
import com.cde.microprograming.product.bo.ProductBO;
import com.cde.microprograming.product.dao.ProductDAO;
import com.cde.microprograming.product.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDAO;

	@Override
	public int createProduct(ProductBO productBO) {
		Product product = new Product(productBO);
		return productDAO.save(product).getId();
	}

	@Override
	public void updateProduct(ProductBO productBO) {
		Optional<Product> productData = productDAO.findById(productBO.getId());
		if (!productData.isPresent()) {
			throw new InventoryNotFoundException("data not found for " + productBO.getId());
		}
		Product product = new Product(productBO);
		productDAO.save(product);
	}

	@Override
	public ProductBO getProduct(int id) {
		Optional<Product> productOptional = productDAO.findById(id);
		return productOptional.map(ProductBO::new).orElse(null);
	}

	@Override
	public List<ProductBO> getAllProducts() {
		List<Product> products = productDAO.findAll();
		return products.stream().map(ProductBO::new).collect(Collectors.toList());
	}

	@Override
	public void deleteProduct(int id) {
		Optional<Product> productOptional = productDAO.findById(id);
		if (!productOptional.isPresent())
			throw new InventoryNotFoundException("data not found: " + id);
		productDAO.deleteById(id);

	}

}
