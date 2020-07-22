package com.cde.microprograming.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cde.microprograming.security.JwtTokenUtil;
import com.cde.microprograming.user.bo.AuthToken;
import com.cde.microprograming.user.bo.LoginUser;
import com.cde.microprograming.user.bo.UserBO;
import com.cde.microprograming.user.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@GetMapping("/user")
	public List<UserBO> getAllUser() {
		return userService.getAllUser();
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/user/{id}")
	public UserBO getUser(@PathVariable int id) {
		return userService.getUser(id);
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PostMapping("/signup")
	public ResponseEntity<String> createUser(@RequestBody UserBO userBO) {
		if (userBO.getId() == 0) {
			userService.createUser(userBO);
			return ResponseEntity.accepted().body("Successfully Created");
		} else {
			return ResponseEntity.unprocessableEntity().body("data already present");
		}
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PutMapping("/user/{id}")
	public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody UserBO userBO) {
		userBO.setId(id);
		userService.updateUser(userBO);
		return ResponseEntity.accepted().body("Successfully Updated");
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return ResponseEntity.accepted().body("Successfully Deleted");
	}

	@PostMapping("/login")
	public ResponseEntity<AuthToken> register(@RequestBody LoginUser loginUser) {

		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String token = jwtTokenUtil.generateToken(authentication);
		return ResponseEntity.ok(new AuthToken(token));
	}

}
