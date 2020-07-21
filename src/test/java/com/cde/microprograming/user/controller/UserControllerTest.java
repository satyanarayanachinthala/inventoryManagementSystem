package com.cde.microprograming.user.controller;

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

import com.cde.microprograming.user.bo.UserBO;
import com.cde.microprograming.user.model.User;
import com.cde.microprograming.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(controllers = UserController.class)
@WithMockUser
public class UserControllerTest {

	@Mock
	UserService userService;

	@InjectMocks
	UserController userController;

	@Autowired
	public MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	User user;

	UserBO userBO;

	@Before
	public void init() {
		user = new User();
		user.setId(1);
		user.setAge(20);
		user.setMobileNumber("1234567890");
		user.setPassword("admin");
		user.setUserName("admin");

		userBO = new UserBO(user);
	}

	@Test
	public void testGetUser() throws Exception {
		when(userService.getUser(1)).thenReturn(userBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}

	@Test
	public void testGetAllUser() throws Exception {
		List<UserBO> userBOList = new ArrayList<UserBO>();
		userBOList.add(userBO);
		when(userService.getAllUser()).thenReturn(userBOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}

	@Test
	public void testCreateUserException() throws Exception {
		ObjectMapper obj = new ObjectMapper();
		String data = obj.writeValueAsString(userBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/signup").accept(MediaType.APPLICATION_JSON)
				.content(data).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}

	@Test
	public void testCreateUser() throws Exception {
		userBO.setId(0);
		ObjectMapper obj = new ObjectMapper();
		String data = obj.writeValueAsString(userBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/signup").accept(MediaType.APPLICATION_JSON)
				.content(data).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}

	@Test
	public void testupdateUser() throws Exception {
		ObjectMapper obj = new ObjectMapper();
		String data = obj.writeValueAsString(userBO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/user/1").accept(MediaType.APPLICATION_JSON)
				.content(data).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}

	@Test
	public void testDeleteUser() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/user/1").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertNotNull(result.getResponse());
	}

//	@Test
//	public void testRegister() throws Exception {
//		LoginUser loginUser = new LoginUser();
//		loginUser.setUserName("admin");
//		loginUser.setPassword("admin");
//		ObjectMapper obj = new ObjectMapper();
//		String data = obj.writeValueAsString(loginUser);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login").accept(MediaType.APPLICATION_JSON)
//				.content(data).contentType(MediaType.APPLICATION_JSON);
//		MvcResult result = mvc.perform(requestBuilder).andReturn();
//		assertNotNull(result.getResponse());
//	}
}
