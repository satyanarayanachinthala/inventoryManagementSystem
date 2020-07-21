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

import com.cde.microprograming.product.bo.ComponentBO;
import com.cde.microprograming.product.bo.PurchasingInformationBO;
import com.cde.microprograming.product.model.Component;
import com.cde.microprograming.product.model.PurchasingInformation;
import com.cde.microprograming.product.service.ComponentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(controllers = ComponentController.class)
@WithMockUser
public class ComponentControllerTest {

	@Mock
	ComponentService componentService;

	@InjectMocks
	ComponentController componentController;

	@Autowired
	public MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(componentController).build();
	}
	
	Component component;
	ComponentBO componentBO;
	
	@Before
	public void init() {
		component = new Component();
		component.setId(1);
		component.setName("computer");
		component.setQuantity(2);
		component.setAvailableQuantity(2);
		component.setUser(1);
		PurchasingInformation purchasingInformation = new PurchasingInformation();
		purchasingInformation.setId(1);
		purchasingInformation.setPrice(100);
		purchasingInformation.setPurchasedFrom("data");
		purchasingInformation.setQuantity(2);
		List<PurchasingInformation> purchasingInformations = new ArrayList<PurchasingInformation>();
		purchasingInformations.add(purchasingInformation);
		component.setPurchasingInformations(purchasingInformations);

		componentBO = new ComponentBO();
		componentBO.setId(1);
		componentBO.setName("computer");
		componentBO.setQuantity(2);
		componentBO.setAvailableQuantity(2);
		componentBO.setUser(1);
		PurchasingInformationBO purchasingInformationBO = new PurchasingInformationBO();
		purchasingInformationBO.setId(1);
		purchasingInformationBO.setPrice(100);
		purchasingInformationBO.setPurchasedFrom("data");
		purchasingInformationBO.setQuantity(2);
		List<PurchasingInformationBO> purchasingInformationBOs = new ArrayList<PurchasingInformationBO>();
		purchasingInformationBOs.add(purchasingInformationBO);
		componentBO.setPurchasingInformations(purchasingInformationBOs);

	}


	@Test
	public void testgetComponent() throws Exception {
		when(componentService.getComponent(1)).thenReturn(componentBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/component/1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}
	
	@Test
	public void testgetComponentException() throws Exception {
		when(componentService.getComponent(2)).thenReturn(null);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/component/2").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}

	@Test
	public void testGetOrders() throws Exception {
		List<ComponentBO> componentBOList = new ArrayList<ComponentBO>();
		componentBOList.add(new ComponentBO());
		when(componentService.getAllComponents()).thenReturn(componentBOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/component").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}
	
	@Test
	public void testCreateComponentException() throws Exception {
		ObjectMapper obj = new ObjectMapper();
		String data = obj.writeValueAsString(componentBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/component").accept(MediaType.APPLICATION_JSON).content(data).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}
	
	@Test
	public void testCreateComponent() throws Exception {
		componentBO.setId(0);
		ObjectMapper obj = new ObjectMapper();
		String data = obj.writeValueAsString(componentBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/component").accept(MediaType.APPLICATION_JSON).content(data).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}

	@Test
	public void testupdateComponent() throws Exception {
		ObjectMapper obj = new ObjectMapper();
		String data = obj.writeValueAsString(componentBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/component/1").accept(MediaType.APPLICATION_JSON).content(data).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}
	
	@Test
	public void testDeleteComponent() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/component/1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}


}
