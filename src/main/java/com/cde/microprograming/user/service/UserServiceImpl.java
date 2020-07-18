package com.cde.microprograming.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cde.microprograming.user.bo.UserBO;
import com.cde.microprograming.user.dao.UserDAO;
import com.cde.microprograming.user.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	@Override
	public User createUser(UserBO userBO) {
		User user = new User(userBO);
		return userDAO.save(user);
	}

	@Override
	public User UpdateUser(UserBO userBO) {
		User user = new User(userBO);
		return userDAO.save(user);
	}

	@Override
	public UserBO getUser(int id) {
		Optional<User> user = userDAO.findById(id);
		if (user.isPresent()) {
			User userData = user.get();
			return new UserBO(userData);
		}
		// TODO
		return null;
	}

	@Override
	public void deleteUser(int id) {
		userDAO.deleteById(id);
	}

}
