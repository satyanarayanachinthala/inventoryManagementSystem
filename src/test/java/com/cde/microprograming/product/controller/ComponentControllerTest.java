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
import com.cde.microprograming.product.service.ComponentService;

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

	@Test
	public void testgetComponent() throws Exception {
		when(componentService.getComponent(1)).thenReturn(new ComponentBO());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/component/1").accept(MediaType.APPLICATION_JSON);
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

}
