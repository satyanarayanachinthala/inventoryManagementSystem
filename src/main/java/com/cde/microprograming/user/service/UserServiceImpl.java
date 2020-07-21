package com.cde.microprograming.user.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cde.microprograming.exception.InventoryNotFoundException;
import com.cde.microprograming.user.bo.UserBO;
import com.cde.microprograming.user.dao.UserDAO;
import com.cde.microprograming.user.model.User;

@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	UserDAO userDAO;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	@Override
	public User createUser(UserBO userBO) {
		User user = new User();
		user.setId(userBO.getId());
		user.setAge(userBO.getAge());
		user.setMobileNumber(userBO.getMobileNumber());
		user.setUserName(userBO.getUserName());
		user.setPassword(bcryptEncoder.encode(userBO.getPassword()));
		return userDAO.save(user);
	}

	@Override
	public User updateUser(UserBO userBO) {
		User user = new User(userBO);
		return userDAO.save(user);
	}

	@Override
	public UserBO getUser(int id) {
		Optional<User> user = userDAO.findById(id);
		if (!user.isPresent()) {
			throw new InventoryNotFoundException("data not found " + id);
		}

		User userData = user.get();
		return new UserBO(userData);
	}

	@Override
	public void deleteUser(int id) {
		userDAO.deleteById(id);
	}

	@Override
	public List<UserBO> getAllUser() {
		List<User> users = userDAO.findAll();
		return users.stream().map(UserBO::new).collect(Collectors.toList());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				getAuthority(user));
	}

	private Set<GrantedAuthority> getAuthority(User user) {
		Set<GrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
		return authorities;
	}

}
