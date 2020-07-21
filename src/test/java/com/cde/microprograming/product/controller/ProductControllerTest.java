package com.cde.microprograming.product.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cde.microprograming.product.bo.ProductBO;
import com.cde.microprograming.product.model.Product;
import com.cde.microprograming.product.model.ProductInventory;
import com.cde.microprograming.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(controllers = ProductController.class)
@WithMockUser
public class ProductControllerTest {
	
	@Mock
	ProductService productService;
	
	@InjectMocks
	ProductController productController;
	
	@Autowired
	public MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(productController).build();
	}
	
	Product product;
	
	ProductBO productBO;
	
	@Before
	public void init() {
		product = new Product();
		product.setId(1);;
		product.setName("laptop");
		product.setQuantity(1);
		product.setComponentId(1);
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
	public void testGetProduct() throws Exception {
		when(productService.getProduct(1)).thenReturn(productBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}
	
	@Test
	public void testGetProductException() throws Exception {
		when(productService.getProduct(2)).thenReturn(null);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product/2").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}
	
	@Test
	public void testGetAllProducts() throws Exception {
		List<ProductBO> products = new ArrayList<ProductBO>();
		products.add(productBO);
		when(productService.getAllProducts()).thenReturn(products);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/product").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}
	
	@Test
	public void testCreateProductException() throws Exception {
		ObjectMapper obj = new ObjectMapper();
		String data = obj.writeValueAsString(productBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/product").accept(MediaType.APPLICATION_JSON).content(data).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}
	
	@Test
	public void testCreateProduct() throws Exception {
		productBO.setId(0);
		ObjectMapper obj = new ObjectMapper();
		String data = obj.writeValueAsString(productBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/product").accept(MediaType.APPLICATION_JSON).content(data).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}

	@Test
	public void testupdateProduct() throws Exception {
		ObjectMapper obj = new ObjectMapper();
		String data = obj.writeValueAsString(productBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/product/1").accept(MediaType.APPLICATION_JSON).content(data).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}
	
	@Test
	public void testDeleteProduct() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/product/1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}


}
