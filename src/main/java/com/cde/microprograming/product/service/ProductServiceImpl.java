package com.cde.microprograming.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cde.microprograming.product.dao.ProductDAO;
import com.cde.microprograming.product.model.Product;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDAO productDAO;

	@Override
	public Product createProduct(Product product) {
		return productDAO.save(product);
	}
	

}
