package com.cde.microprograming.user.service;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cde.microprograming.user.bo.UserBO;
import com.cde.microprograming.user.dao.UserDAO;
import com.cde.microprograming.user.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@Mock
	UserDAO userDAO;

	@Mock
	private BCryptPasswordEncoder bcryptEncoder;

	@InjectMocks
	UserServiceImpl userServiceImpl;

	User user;
	UserBO userBO;

	@Before
	public void init() {
		user = new User();
		user.setId(1);
		user.setAge(20);
		user.setMobileNumber("99999999");
		user.setPassword("admin");
		user.setUserName("admin");

		userBO = new UserBO(user);
	}

	@Test
	public void testCreateUser() {
		when(userDAO.save(ArgumentMatchers.any(User.class))).thenReturn(user);
		assertEquals("admin", userServiceImpl.createUser(userBO).getUserName());
	}

	@Test
	public void testUpdateUser() {
		when(userDAO.save(ArgumentMatchers.any(User.class))).thenReturn(user);
		assertEquals("admin", userServiceImpl.updateUser(userBO).getUserName());
	}

	@Test
	public void testGetUser() {
		when(userDAO.findById(1)).thenReturn(Optional.of(user));
		assertEquals("admin", userServiceImpl.getUser(1).getUserName());
	}

	@Test
	public void testGetAllUsers() {
		List<User> users = new ArrayList<User>();
		users.add(user);
		when(userDAO.findAll()).thenReturn(users);
		assertEquals("admin", userServiceImpl.getAllUser().get(0).getUserName());
	}

	@Test
	public void testDeleteUser() {
		userServiceImpl.deleteUser(13);
		assertEquals("admin", user.getUserName());
	}

}
