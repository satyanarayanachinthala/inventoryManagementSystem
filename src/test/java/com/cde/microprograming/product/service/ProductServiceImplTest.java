package com.cde.microprograming.product.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.cde.microprograming.exception.InventoryNotFoundException;
import com.cde.microprograming.product.bo.ProductBO;
import com.cde.microprograming.product.dao.ProductDAO;
import com.cde.microprograming.product.model.Product;
import com.cde.microprograming.product.model.ProductInventory;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

	@Mock
	ProductDAO productDAO;

	@InjectMocks
	ProductServiceImpl productServiceImpl;

	Product product;
	ProductBO productBO;

	@Before
	public void init() {
		product = new Product();
		product.setId(1);
		product.setName("laptop");
		product.setStatus("in progress");
		product.setQuantity(1);
		product.setType("ele");
		product.setComponentId(1);
		product.setRawMaterialId(1);
		ProductInventory productInventory = new ProductInventory();
		productInventory.setId(1);
		productInventory.setComponentId(1);
		productInventory.setRawMaterialId(1);
		productInventory.setType("ele");
		List<ProductInventory> productInventories = new ArrayList<>();
		productInventories.add(productInventory);
		product.setProductInventories(productInventories);
		productBO = new ProductBO(product);
	}

	@Test
	public void testGetProduct() {
		when(productDAO.findById(1)).thenReturn(Optional.of(product));
		assertEquals("laptop", productServiceImpl.getProduct(1).getName());
	}

	@Test
	public void testGetProductForNull() {
		when(productDAO.findById(2)).thenReturn(Optional.empty());
		assertEquals(null, productServiceImpl.getProduct(2));
	}

	@Test
	public void testGetAllProducts() {
		List<Product> products = new ArrayList<>();
		products.add(product);
		when(productDAO.findAll()).thenReturn(products);
		assertEquals("laptop", productServiceImpl.getAllProducts().get(0).getName());
	}

	@Test
	public void testDeleteProduct() {
		when(productDAO.findById(1)).thenReturn(Optional.of(product));
		productServiceImpl.deleteProduct(1);
		assertEquals("laptop", product.getName());
	}

	@Test(expected = InventoryNotFoundException.class)
	public void testDeleteProductException() {
		when(productDAO.findById(2)).thenReturn(Optional.empty());
		productServiceImpl.deleteProduct(2);
	}

	@Test(expected = InventoryNotFoundException.class)
	public void testUpdateProductException() {
		productBO.setId(2);
		when(productDAO.findById(2)).thenReturn(Optional.empty());
		productServiceImpl.updateProduct(productBO);
	}

	@Test
	public void testUpdateComponent() {
		when(productDAO.findById(1)).thenReturn(Optional.of(product));
		productServiceImpl.updateProduct(productBO);
		assertEquals("laptop", product.getName());
	}

	@Test
	public void testCreateProduct() {
		when(productDAO.save(ArgumentMatchers.any(Product.class))).thenReturn(product);
		assertEquals(1, productServiceImpl.createProduct(productBO));
	}

}
