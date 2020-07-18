package com.cde.microprograming.product.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.cde.microprograming.product.model.RawMaterial;
import com.cde.microprograming.product.service.RawMaterialService;

@SpringBootTest
class RawMaterialControllerTest {
	
	@Autowired
	RawMaterialController rawMaterialController;
	
	@MockBean
	RawMaterialService rawMaterialService;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	void test() throws Exception {
		/*
		 * RawMaterial rawMaterial = new RawMaterial();
		 * when(rawMaterialService.createRawMaterial(rawMaterial)).thenReturn(
		 * rawMaterial);
		 * this.mockMvc.perform(post("/rawMaterial")).andDo(print()).andExpect(status().
		 * isOk());
		 */
		assertThat(rawMaterialController).isNotNull();
	}

}
