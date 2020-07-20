package com.cde.microprograming.product.service;

import java.util.List;

import com.cde.microprograming.product.bo.ProductBO;

public interface ProductService {

	int createProduct(ProductBO productBO);

	void updateProduct(ProductBO productBO);

	ProductBO getProduct(int id);

	List<ProductBO> getAllProducts();

	void deleteProduct(int id);

}
