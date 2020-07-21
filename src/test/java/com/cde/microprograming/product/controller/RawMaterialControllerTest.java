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

import com.cde.microprograming.product.bo.RawMaterialBO;
import com.cde.microprograming.product.model.PurchasingInformation;
import com.cde.microprograming.product.model.RawMaterial;
import com.cde.microprograming.product.service.RawMaterialService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(controllers = RawMaterialController.class)
@WithMockUser
public class RawMaterialControllerTest {

	@Mock
	RawMaterialService rawMaterialService;

	@InjectMocks
	RawMaterialController rawMaterialController;

	@Autowired
	public MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(rawMaterialController).build();
	}

	RawMaterial rawMaterial;
	RawMaterialBO rawMaterialBO;

	@Before
	public void init() {
		rawMaterial = new RawMaterial();
		rawMaterial.setId(1);
		rawMaterial.setName("glass");
		rawMaterial.setQuantity(2);
		rawMaterial.setAvailableQuantity(2);
		PurchasingInformation purchasingInformation = new PurchasingInformation();
		purchasingInformation.setId(1);
		purchasingInformation.setPrice(100);
		purchasingInformation.setPurchasedFrom("data");
		purchasingInformation.setQuantity(2);
		List<PurchasingInformation> purchasingInformations = new ArrayList<PurchasingInformation>();
		purchasingInformations.add(purchasingInformation);

		rawMaterial.setPurchasingInformations(purchasingInformations);

		rawMaterialBO = new RawMaterialBO(rawMaterial);
	}

	@Test
	public void testRawMaterial() throws Exception {
		when(rawMaterialService.getRawMaterial(1)).thenReturn(rawMaterialBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rawMaterial/1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}

	@Test
	public void testGetrawMaterailException() throws Exception {
		when(rawMaterialService.getRawMaterial(2)).thenReturn(null);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rawMaterial/2").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}

	@Test
	public void testGetRawMaterials() throws Exception {
		List<RawMaterialBO> rawMaterialBOList = new ArrayList<RawMaterialBO>();
		rawMaterialBOList.add(rawMaterialBO);
		when(rawMaterialService.getAllRawMaterials()).thenReturn(rawMaterialBOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rawMaterial").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}

	@Test
	public void testCreateRawMaterialException() throws Exception {
		ObjectMapper obj = new ObjectMapper();
		String data = obj.writeValueAsString(rawMaterialBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/rawMaterial").accept(MediaType.APPLICATION_JSON)
				.content(data).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}

	@Test
	public void testCreateRawMaterial() throws Exception {
		rawMaterialBO.setId(0);
		ObjectMapper obj = new ObjectMapper();
		String data = obj.writeValueAsString(rawMaterialBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/rawMaterial").accept(MediaType.APPLICATION_JSON)
				.content(data).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}

	@Test
	public void testUpdateRawmaterial() throws Exception {
		ObjectMapper obj = new ObjectMapper();
		String data = obj.writeValueAsString(rawMaterialBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/rawMaterial/1").accept(MediaType.APPLICATION_JSON)
				.content(data).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}

	@Test
	public void testDeleteRawmaterial() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/rawMaterial/1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}

}
