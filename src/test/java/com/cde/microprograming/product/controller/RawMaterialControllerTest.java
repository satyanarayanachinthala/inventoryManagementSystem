package com.cde.microprograming.product.controller;

import static org.junit.Assert.assertEquals;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cde.microprograming.product.bo.PurchasingInformationBO;
import com.cde.microprograming.product.bo.RawMaterialBO;
import com.cde.microprograming.product.model.PurchasingInformation;
import com.cde.microprograming.product.model.RawMaterial;
import com.cde.microprograming.product.service.RawMaterialService;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(RawMaterialController.class)
class RawMaterialControllerTest {

	@InjectMocks
	RawMaterialController rawMaterialController;

	@Mock
	RawMaterialService rawMaterialService;

	protected MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
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

		rawMaterialBO = new RawMaterialBO();

		rawMaterialBO.setId(1);
		rawMaterialBO.setName("glass");
		rawMaterialBO.setQuantity(2);
		rawMaterialBO.setAvailableQuantity(2);
		PurchasingInformationBO purchasingInformationBO = new PurchasingInformationBO();
		purchasingInformationBO.setId(1);
		purchasingInformationBO.setPrice(100);
		purchasingInformationBO.setPurchasedFrom("data");
		purchasingInformationBO.setQuantity(2);
		List<PurchasingInformationBO> purchasingInformationBOs = new ArrayList<PurchasingInformationBO>();
		purchasingInformationBOs.add(purchasingInformationBO);

		rawMaterialBO.setPurchasingInformations(purchasingInformationBOs);

	}

	@Test
	public void testGetRawMaterial() throws Exception {
		String uri = "/products";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

//		when(rawMaterialService.getRawMaterial(1)).thenReturn(rawMaterialBO);
//		assertEquals("glass", rawMaterialController.getRawMaterial(1));
	}

}
