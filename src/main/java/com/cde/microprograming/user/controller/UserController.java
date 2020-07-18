package com.cde.microprograming.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cde.microprograming.user.bo.UserBO;
import com.cde.microprograming.user.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/user")
	public ResponseEntity<String> createUser(@RequestBody UserBO userBO){
		if(userBO.getId() == 0) {
			userService.createUser(userBO);
			return ResponseEntity.accepted().body("Successfully Created");
		} else {
			return ResponseEntity.unprocessableEntity().body("data already present");
		}
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<String> UpdateUser(@PathVariable int id, @RequestBody UserBO userBO){
		userBO.setId(id);
		userService.UpdateUser(userBO);
		return ResponseEntity.accepted().body("Successfully Updated");
	}
	
	@GetMapping("/user/{id}")
	public UserBO getUser(@PathVariable int id) {
		return userService.getUser(id);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return ResponseEntity.accepted().body("Successfully Deleted");
	}
	

}
