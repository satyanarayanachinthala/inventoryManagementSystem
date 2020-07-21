package com.cde.microprograming.user.service;

import java.util.List;

import com.cde.microprograming.user.bo.UserBO;
import com.cde.microprograming.user.model.User;

public interface UserService {

	User createUser(UserBO userBO);

	User updateUser(UserBO userBO);

	UserBO getUser(int id);

	void deleteUser(int id);

	List<UserBO> getAllUser();

}
